package com.bazan.carlend.booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookingRepository extends JpaRepository<Booking, Integer> {
}
