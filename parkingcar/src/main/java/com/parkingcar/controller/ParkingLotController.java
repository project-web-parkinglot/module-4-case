package com.parkingcar.controller;

import com.parkingcar.model.Customer.Customer;
import com.parkingcar.service.customer.CustomerService;
import com.parkingcar.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Id;
import java.util.List;

@Controller
public class ParkingLotController {

    @Autowired
    private ICustomerService customerService;
    @GetMapping("/")
    public String showParkingLotPage(){
        return "parkinglot";
    }
    @GetMapping("/list")
    public String showList(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "/customer/list-customer";
    }


}
