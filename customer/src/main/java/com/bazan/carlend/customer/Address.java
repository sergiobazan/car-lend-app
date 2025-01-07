package com.bazan.carlend.customer;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String street, String houseNumber, String zipCode) {
}
