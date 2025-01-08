package com.bazan.carlend.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IBookingRepository extends JpaRepository<Booking, Integer> {

    @Query("""
            SELECT
                 CASE
                     WHEN COUNT(b) > 0 THEN true
                     ELSE false
                 END
            FROM Booking b
            WHERE b.vehicle.id = :vehicleId
            AND (:start < DATE(b.endDate) AND :end > DATE(b.startDate))
    """)
    boolean isOverlapping(
            @Param("vehicleId") int vehicleId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

    List<Booking> findByClientId(int clientId);
}
