package com.parkingcar.controller.customer;

import com.parkingcar.dto.customer.CustomerDTO;
import com.parkingcar.dto.customer.ICustomerDTO;
import com.parkingcar.model.account.Account;
import com.parkingcar.model.account.Role;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.model.packageRent.PackageRent;
import com.parkingcar.service.account.IAccountService;
import com.parkingcar.service.customer.ICustomerService;
import com.parkingcar.service.packageRent.IPackageRentService;
import com.parkingcar.service.packageRent.PackageRentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IPackageRentService packageRentService;

    //data giả
//    Account account = new Account(1,"dinhlong1110","abcd1234","long1110dn@gmail.com",false,new Role(2,"ROLE_CUSTOMER"),null);
    private Account getAccount(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Account account = accountService.findAccountByUserName(currentUserName);
            return account;
        }
        return null;
    }
    @GetMapping("/detail")
    public String showCustomerDetail(Model model){
        Account account = getAccount();
        Customer customer = customerService.findCustomerByAccountId(account.getId());
        model.addAttribute("customer",customer);
        model.addAttribute("account",account);
        return "/customer/detail-customer";
    }
    @GetMapping("/edit")
    public String showFormEdit(@RequestParam int id, Model model){
        Account account = getAccount();
        Customer customer = customerService.findById(id);
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        model.addAttribute("customerDTO",customerDTO);
        model.addAttribute("account",account);
        return "/customer/edit-customer";
    }

    @PostMapping("/updatecustomer")
    public String updateCustomer(@Validated  @ModelAttribute CustomerDTO customerDTO, Model model ,  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        Account account = getAccount();
        new CustomerDTO().validate(customerDTO,bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("customerDTO",customerDTO);
            model.addAttribute("account",account);
            return "/customer/edit-customer";
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        customer.setAccount(account);
        customerService.saveCustomer(customer);
        redirectAttributes.addFlashAttribute("message","Sửa thành công");
        return "redirect:/customer/detail";
    }
    @GetMapping("/showbill")
    public String showListBill(Model model){
        Account account = getAccount();
        List<ICustomerDTO> customerDTOList = customerService.findCustomerByBills(account.getId());
        List<PackageRent>  packageRentList = packageRentService.findAll();
        model.addAttribute("customerDTOList",customerDTOList);
        model.addAttribute("packageRentList", packageRentList);
        return "/customer/customer-bill";
    }
}

