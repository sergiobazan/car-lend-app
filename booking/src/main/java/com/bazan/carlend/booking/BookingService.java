package com.bazan.carlend.booking;

import com.bazan.carlend.client.Client;
import com.bazan.carlend.client.IClientRepository;
import com.bazan.carlend.vehicle.IVehicleRepository;
import com.bazan.carlend.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookingService implements IBookingService {

    private final IBookingRepository bookingRepository;
    private final IClientRepository clientRepository;
    private final IVehicleRepository vehicleRepository;
    private final IPriceCalculator priceCalculator;

    @Override
    public BookingResponse book(BookingRequest bookingRequest) {
        Client client = clientRepository.findById(bookingRequest.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Vehicle vehicle = vehicleRepository.findById(bookingRequest.vehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        var totalDays = bookingRequest.endDate().getDayOfYear() - bookingRequest.startDate().getDayOfYear();
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

        var bookingSaved = bookingRepository.save(booking);

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
}
