package com.bazan.carlend.booking;

import com.bazan.carlend.client.Client;
import com.bazan.carlend.client.IClientRepository;
import com.bazan.carlend.kafka.BookingConfirmation;
import com.bazan.carlend.kafka.BookingProducer;
import com.bazan.carlend.vehicle.IVehicleRepository;
import com.bazan.carlend.vehicle.Vehicle;
import com.bazan.carlend.vehicle.VehicleStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class BookingService implements IBookingService {

    private final IBookingRepository bookingRepository;
    private final IClientRepository clientRepository;
    private final IVehicleRepository vehicleRepository;
    private final IPriceCalculator priceCalculator;
    private final BookingProducer bookingProducer;

    @Override
    public BookingResponse book(BookingRequest bookingRequest) {
        Client client = clientRepository.findById(bookingRequest.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Vehicle vehicle = vehicleRepository.findById(bookingRequest.vehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (bookingRepository.isOverlapping(
                bookingRequest.vehicleId(),
                bookingRequest.startDate().toLocalDate(),
                bookingRequest.endDate().toLocalDate()))
        {
            throw new RuntimeException("Overlapping vehicle");
        }

        var totalDays = getTotalDays(bookingRequest);
        var totalPrice = priceCalculator.calculatePrice(vehicle.getPricePerDay(), totalDays);

        var booking = Booking.builder()
                .startDate(bookingRequest.startDate())
                .endDate(bookingRequest.endDate())
                .instructions(bookingRequest.instructions())
                .status(BookingStatus.RESERVED)
                .totalAmount(totalPrice)
                .client(client)
                .vehicle(vehicle)
                .build();

        vehicle.setStatus(VehicleStatus.BUSY);

        var bookingSaved = bookingRepository.save(booking);

        bookingProducer.sendBooking(new BookingConfirmation(
                bookingSaved.getId(),
                vehicle.getId(),
                vehicle.getStatus()
        ));

        return BookingMapper.fromBooking(bookingSaved);
    }

    @Override
    public List<BookingResponse> findAll() {
        return bookingRepository
                .findAll()
                .stream()
                .map(BookingMapper::fromBooking)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> findByClientId(int id) {
        return bookingRepository.findByClientId(id)
                .stream()
                .map(BookingMapper::fromBooking)
                .toList();
    }

    private static int getTotalDays(BookingRequest bookingRequest) {
        return bookingRequest.endDate().getDayOfYear() - bookingRequest.startDate().getDayOfYear();
    }
}
