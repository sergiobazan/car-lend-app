package com.bazan.carlend.booking;

public class BookingMapper {

    public static BookingResponse fromBooking(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getStatus(),
                booking.getTotalAmount(),
                booking.getInstructions(),
                new ClientResponse(
                        booking.getClient().getId(),
                        booking.getClient().getFullName(),
                        booking.getClient().getEmail()
                ),
                new VehicleResponse(
                        booking.getVehicle().getId(),
                        booking.getVehicle().getModel(),
                        booking.getVehicle().getCategory()
                )
        );
    }
}
