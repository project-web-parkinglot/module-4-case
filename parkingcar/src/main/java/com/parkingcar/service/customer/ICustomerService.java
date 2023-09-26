package com.parkingcar.service.customer;

import com.parkingcar.model.Customer.Customer;

import java.util.List;

public interface ICustomerService {
    Customer findById(int id);
    List<Customer> findAll();
}
