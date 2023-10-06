package com.parkingcar.controller.bill;

import com.parkingcar.dto.customer.ICustomerDTO;
import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.model.packageRent.PackageRent;
import com.parkingcar.service.bill.IBillService;
import com.parkingcar.service.customer.ICustomerService;
import com.parkingcar.service.packageRent.IPackageRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private IBillService billService;
    @Autowired
    private IPackageRentService packageRentService;
    @Autowired
    private ICustomerService customerService;


    @GetMapping("/update")
    public String showFormUpdateBill(@RequestParam int id, Model model){
        Bill bill = billService.findById(id);
        List<PackageRent> packageRentList = packageRentService.findAll();
        model.addAttribute("bill",bill);
        model.addAttribute("packageRentList",packageRentList);
        return "bill/show-form-bill";
    }
    @PostMapping("/edit")
    public String editBill(@ModelAttribute Bill bill,  RedirectAttributes redirectAttributes){
        bill.setStatus("0");
        bill.setMoneyPay(bill.getPackageRent().getMoneyRent());
        bill.setTimePay(LocalDate.now());
        bill.setEndDate(bill.getEndDate().plusDays(bill.getPackageRent().getDay()));
        billService.saveBill(bill);
        redirectAttributes.addFlashAttribute("message","ok");
        return "redirect:/customer/showBill";
    }

}
