package com.parkingcar.controller.admin;

import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.bill.IBillDTO;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.model.pakingLot.ParkingLot;
import com.parkingcar.repository.parkinglot.IParkingLotStatusRepository;
import com.parkingcar.service.admin.IParkingLotStatusService;
import com.parkingcar.service.admin.IParkingService;
import com.parkingcar.service.bill.IBillService;
import com.parkingcar.service.customer.ICustomerService;
import com.parkingcar.service.parkinglot.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IBillService billService;
    @Autowired
    private IParkingLotService parkingLotService;
    @Autowired
    private IParkingLotStatusService parkingLotStatusService;
    @Autowired
    private IParkingService parkingService;



    @GetMapping
    public String showForm(Model model) {
        int available = parkingService.countByParkingLotStatus(1);
        int block = parkingService.countByParkingLotStatus(2);
        int owner = parkingService.countByParkingLotStatus(3);
        int waiting = parkingService.countByParkingLotStatus(4);
        model.addAttribute("available",available);
        model.addAttribute("block",block);
        model.addAttribute("owner",owner);
        model.addAttribute("waiting",waiting);
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
    @PostMapping("/approve")
    public String approveBill(@RequestParam int billId, @RequestParam int parkingId, RedirectAttributes redirectAttributes){

        Bill bill = billService.findById(billId);
        ParkingLot parkinglot = parkingLotService.getParkingById(parkingId);
        bill.setStatus("1");
        parkinglot.setParkingLotStatus(parkingLotStatusService.getParkingLotStatusById(3));
        if (bill.getEndDate() == null && bill.getTimePay() == null){
            bill.setEndDate(LocalDate.now().plusDays(bill.getPackageRent().getDay()));
            bill.setMoneyPay(bill.getPackageRent().getMoneyRent());
            bill.setTimePay(LocalDate.now());
        }
        billService.saveBill(bill);
        redirectAttributes.addFlashAttribute("statusPage",1);
        return "redirect:/admin/listapproved";
    }

}
