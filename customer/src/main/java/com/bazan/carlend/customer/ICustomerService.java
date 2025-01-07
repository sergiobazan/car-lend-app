package com.bazan.carlend.customer;

import java.util.List;

public interface ICustomerService {
    CustomerResponse create(CreateCustomerRequest customerRequest);
    List<CustomerResponse> getAll();
}
