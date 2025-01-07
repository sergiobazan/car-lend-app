package com.bazan.carlend.kafka;

public record CustomerCreated(
        int id,
        String firstName,
        String lastName,
        String email
) {
}
