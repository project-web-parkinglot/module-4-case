package com.parkingcar.service.customer;

import com.parkingcar.dto.customer.CustomerDTO;
import com.parkingcar.dto.customer.ICustomerDTO;
import com.parkingcar.model.customer.Customer;

import java.util.List;

public interface ICustomerService {
    Customer findById(int id);
    List<Customer> findAll();

   Customer findCustomerByAccountId(int id);

   void saveCustomer(Customer customer);

    List<ICustomerDTO> findCustomerByBills(int id);

    void removeCustomer(int id);



}
