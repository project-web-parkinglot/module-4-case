package com.parkingcar.controller.customer;

import com.parkingcar.dto.customer.CustomerDTO;
import com.parkingcar.model.account.Account;
import com.parkingcar.model.account.Role;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.service.account.IAccountService;
import com.parkingcar.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAccountService accountService;

    //data giả
    Account account = new Account(1,"dinhlong1110","abcd1234","long1110dn@gmail.com",false,new Role(2,"ROLE_CUSTOMER"),null);

    @GetMapping("/detail")
    public String showCustomerDetail(Model model){
        Customer customer = customerService.findCustomerByAccountId(account.getId());
        model.addAttribute("customer",customer);
        return "/customer/detail-customer";
    }
    @GetMapping("/edit")
    public String showFormEdit(@RequestParam int id, Model model, Principal principal){
//        String name = principal.getName();
//        Account account1 = accountService.findAccountByUserName(name);
        Customer customer = customerService.findById(id);
//        customer.setAccount(account1);
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        model.addAttribute("customerDTO",customerDTO);
        return "/customer/edit-customer";
    }

    @PostMapping("/updatecustomer")
    public String updateCustomer(@Validated  @ModelAttribute CustomerDTO customerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes,Principal principal){
        new CustomerDTO().validate(customerDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return "/customer/edit-customer";
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
//        String name = principal.getName();
//        Account account1 = accountService.findAccountByUserName(name);
        customer.setAccount(account);
        customerService.saveCustomer(customer);
        redirectAttributes.addFlashAttribute("message","Sửa thành công");
        return "redirect:/customer/detail";
    }
}

