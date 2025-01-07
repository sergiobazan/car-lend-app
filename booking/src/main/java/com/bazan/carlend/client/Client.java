package com.bazan.carlend.client;

import com.bazan.carlend.booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {
    @Id
    private int id;

    private String fullName;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "client", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();
}
