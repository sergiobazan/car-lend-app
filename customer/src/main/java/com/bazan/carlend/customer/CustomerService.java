package com.bazan.carlend.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    public CustomerResponse create(CreateCustomerRequest customerRequest) {
        var customer = Customer
                .builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .address(new Address(
                        customerRequest.street(),
                        customerRequest.houseNumber(),
                        customerRequest.zipCode()))
                .build();

        var createdCustomer = customerRepository.save(customer);

        return CustomerMapper.fromCustomer(createdCustomer);
    }

    @Override
    public List<CustomerResponse> getAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(CustomerMapper::fromCustomer)
                .collect(Collectors.toList());
    }
}
