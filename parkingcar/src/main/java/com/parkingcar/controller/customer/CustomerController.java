package com.parkingcar.controller.customer;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.account.Role;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.service.accountService.IAccountService;
import com.parkingcar.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAccountService accountService;

    //data giả
    Account account = new Account();

    @GetMapping("/detail")
    public String showCustomerDetail(Model model){
        Customer customer = customerService.findCustomerByAccountId(account.getId());
        model.addAttribute("customer",customer);
        return "/customer/detail-customer";
    }
}

