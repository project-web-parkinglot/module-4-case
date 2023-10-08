package com.parkingcar.controller.admin;

import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.bill.IBillDTO;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.service.bill.IBillService;
import com.parkingcar.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IBillService billService;


    @GetMapping
    public String showForm() {
        return "/page_admin/index";
    }

    @GetMapping("/showlistcustomer")
    public String showListCustomer(Model model){
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customerList",customerList);
        return "/page_admin/list-customer";
    }
    @GetMapping("/deletecustomer")
    public String deleteCustomer(@RequestParam int id, RedirectAttributes redirectAttributes){
        customerService.removeCustomer(id);
        redirectAttributes.addFlashAttribute("message","Delete success");
        return "redirect:/admin/showlistcustomer";
    }
    @GetMapping("/listapproved")
    public String showListApproved(Model model){
        List<Bill> billList = billService.getBillByStatus("0");
        model.addAttribute("billList",billList);
        return "/page_admin/list-approved";
    }

}
