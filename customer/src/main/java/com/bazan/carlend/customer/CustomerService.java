package com.bazan.carlend.customer;

import com.bazan.carlend.kafka.CustomerCreated;
import com.bazan.carlend.kafka.CustomerProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final CustomerProducer customerProducer;

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

        customerProducer.sendCustomer(
                new CustomerCreated(
                        createdCustomer.getId(),
                        createdCustomer.getFirstName(),
                        createdCustomer.getLastName(),
                        createdCustomer.getEmail()
                )
        );

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
