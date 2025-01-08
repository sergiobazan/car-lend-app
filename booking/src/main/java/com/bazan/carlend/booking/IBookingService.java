package com.bazan.carlend.booking;

import java.util.List;

public interface IBookingService {
    BookingResponse book(BookingRequest bookingRequest);
    List<BookingResponse> findAll();
    List<BookingResponse> findByClientId(int id);
}
