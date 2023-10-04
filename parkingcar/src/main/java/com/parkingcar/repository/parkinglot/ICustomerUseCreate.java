package com.parkingcar.repository.parkinglot;

import com.parkingcar.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerUseCreate extends JpaRepository<Customer, Integer> {
    Customer getCustomerByAccount_Id(Integer accountId);
}
