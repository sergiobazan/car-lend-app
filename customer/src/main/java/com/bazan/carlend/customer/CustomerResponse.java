package com.bazan.carlend.customer;

public record CustomerResponse(
        int id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
