package com.parkingcar.repository.customer;

import com.parkingcar.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
        Customer findCustomerByAccount_Id(int id);
}
