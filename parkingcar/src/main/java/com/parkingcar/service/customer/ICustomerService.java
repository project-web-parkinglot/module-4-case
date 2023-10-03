package com.parkingcar.service.customer;

import com.parkingcar.model.customer.Customer;

import java.util.List;

public interface ICustomerService {
    Customer findById(int id);
    List<Customer> findAll();

   Customer findCustomerByAccountId(int id);
}
