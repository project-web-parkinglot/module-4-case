package com.parkingcar.service.customer;

import com.parkingcar.dto.customer.CustomerDTO;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService{

    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerByAccountId(int id) {
        return customerRepository.findCustomerByAccount_Id(id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

}
