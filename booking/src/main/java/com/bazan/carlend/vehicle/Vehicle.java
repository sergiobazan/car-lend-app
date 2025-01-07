package com.bazan.carlend.vehicle;

import com.bazan.carlend.booking.Booking;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    private int id;

    private String model;
    private BigDecimal pricePerDay;
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
    private String category;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonBackReference
    private List<Booking> bookings = new ArrayList<>();
}
