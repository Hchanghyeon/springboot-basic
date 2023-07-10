package com.programmers.springweekly.domain.customer;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Customer {

    private final UUID customerId;
    private final String customerName;
    private final String customerEmail;
    private final CustomerType customerType;

    @Builder
    public Customer(UUID customerId, String customerName, String customerEmail, CustomerType customerType) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerType = customerType;
    }

}
