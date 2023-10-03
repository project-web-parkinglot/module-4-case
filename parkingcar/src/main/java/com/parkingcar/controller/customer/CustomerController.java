package com.parkingcar.controller.customer;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.account.Role;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.service.account.IAccountService;
import com.parkingcar.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAccountService accountService;

    //data giả
    Account account = new Account(1,"dinhlong1110","abcd1234","long1110dn@gmail.com",0,new Role(1,"Customer"));

    @GetMapping("/detail")
    public String showCustomerDetail(Model model){
        Customer customer = customerService.findCustomerByAccountId(account.getId());
        model.addAttribute("customer",customer);
        return "/customer/detail-customer";
    }
}

