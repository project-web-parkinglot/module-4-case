package com.parkingcar.controller.parkinglot;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.account.Role;
import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.packageRent.PackageRent;
import com.parkingcar.model.pakingLot.ParkingLot;
import com.parkingcar.service.account.IAccountService;
import com.parkingcar.service.parkinglot.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ParkingLotController {

    @Autowired
    private IParkingLotService parkingLotService;
    @Autowired
    private IAccountService accountService;
//    Account account = new Account(1, "thang_quoc", "aaa", "a@gmail.com", true, new Role(2, "CUSTOMER"), null);

    @GetMapping("/")
    public String showParkingLotPage(Model model, Principal principal) {
        String name = principal.getName();
        Account account = accountService.findAccountByUserName(name);
        int role;
        if (account == null) {
            role = 0;
        } else {
            role = account.getRole().getId();
        }
        model.addAttribute("role", role);
        model.addAttribute("account", account);
        return "parkinglot";
    }
    @GetMapping("/parking/create/{name}")
    public String showFormCreateParking(@PathVariable String name, Model model,Principal principal) {
        String accountName = principal.getName();
        Account account = accountService.findAccountByUserName(accountName);
        if (account.getRole().getId() == 2) {
            ParkingLot parkingLot = parkingLotService.findByName(name);
            model.addAttribute("parking", parkingLot);
            model.addAttribute("account", account);
            model.addAttribute("customer", parkingLotService.getCustomerByAccountId(account.getId()));

            List<String> availableParking = parkingLotService.getAvailableParking();
            model.addAttribute("arrayAvailableB1", availableParking.get(0));
            model.addAttribute("arrayAvailableB2", availableParking.get(1));

            List<PackageRent> packageRents = parkingLotService.getPackage();
            model.addAttribute("package", packageRents);

            return "parkinglot_create";
        }
        return "redirect:/";
    }

    @PostMapping("/parking/create")
    public String createHireRequest(@RequestParam Integer parkingId,
                                    @RequestParam String linkimg,
                                    @RequestParam String licensePlate,
                                    @RequestParam Integer pack, Principal principal
                                    ) {
        String name = principal.getName();
        Account account = accountService.findAccountByUserName(name);
        parkingLotService.createNewRequest(account, parkingId, linkimg, licensePlate, pack);
        return "redirect:/";
    }
}
