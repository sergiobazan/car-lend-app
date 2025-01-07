package com.bazan.carlend.customer;

public record CreateCustomerRequest(
        String firstName,
        String lastName,
        String email,
        String street,
        String houseNumber,
        String zipCode
) {
}
