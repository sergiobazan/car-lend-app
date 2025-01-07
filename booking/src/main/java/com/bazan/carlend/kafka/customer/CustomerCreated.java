package com.bazan.carlend.kafka.customer;

public record CustomerCreated(
        int id,
        String firstName,
        String lastName,
        String email
) {
}
