package com.bazan.carlend.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> create(@RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok(bookingService.book(bookingRequest));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAll() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<List<BookingResponse>> getBookingsByClientId(@PathVariable int id) {
        List<BookingResponse> bookings = bookingService.findByClientId(id);
        return ResponseEntity.ok(bookings);
    }
}
