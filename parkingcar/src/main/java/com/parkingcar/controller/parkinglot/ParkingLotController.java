package com.parkingcar.controller.parkinglot;
import com.parkingcar.model.account.Account;
import com.parkingcar.model.account.Role;
import com.parkingcar.model.pakingLot.ParkingLot;
import com.parkingcar.service.account.IAccountService;
import com.parkingcar.service.parkinglot.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ParkingLotController {

    @Autowired
    private IParkingLotService parkingLotService;
    @Autowired
    private IAccountService accountService;


    Account account = new Account(1, "test", "aaa", "a@gmail.com", true, new Role(3, "amin"),null);





    @GetMapping("/")
    public String showParkingLotPage(Model model){
        int role;
        if (account == null){
            role = 0;
        } else {
            role = account.getRole().getId();
        }
        model.addAttribute("role", role);

        List<String> blockParking = parkingLotService.getBlockParking();
        List<String> availableParking = parkingLotService.getAvailableParking();

        model.addAttribute("arrayBlockB1", blockParking.get(0));
        model.addAttribute("arrayBlockB2", blockParking.get(1));
        model.addAttribute("arrayAvailableB1", availableParking.get(0));
        model.addAttribute("arrayAvailableB2", availableParking.get(1));

        if (account.getRole().getId() >= 2){
            List<String> adminCheck = parkingLotService.getCheckParking();

            model.addAttribute("ownParkingB1", adminCheck.get(0));
            model.addAttribute("ownParkingB2", adminCheck.get(1));

        } else if (account.getRole().getId() == 1) {
            List<String> customerParking = parkingLotService.getMyParking(account);

            model.addAttribute("ownParkingB1", customerParking.get(0));
            model.addAttribute("ownParkingB2", customerParking.get(1));
            model.addAttribute("otherParkingB1", customerParking.get(2));
            model.addAttribute("otherParkingB2", customerParking.get(3));
        } else {
            List<String> anonymousParking = parkingLotService.getAnonymousParking();

            model.addAttribute("otherParkingB1", anonymousParking.get(0));
            model.addAttribute("otherParkingB2", anonymousParking.get(1));
        }
        return "parkinglot";
    }

    @GetMapping("/parking/lock/{name}")
    public String lockParking(@PathVariable String name){
        if (account.getRole().getId() >= 2){
            try {
                parkingLotService.lockParking(name);
            } catch (IllegalAccessException e) {
                System.out.println(e);
            }
        }
        return "redirect:/";
    }
    @GetMapping("/parking/unlock/{name}")
    public String unlockParking(@PathVariable String name){
        if (account.getRole().getId() >= 2){
            try {
                parkingLotService.unlockParking(name);
            } catch (IllegalAccessException e) {
                System.out.println(e);
            }
        }
        return "redirect:/";
    }
    @GetMapping("/parking/endlease/{name}")
    public String endLeaseParking(@PathVariable String name){
        try {
            parkingLotService.endLeaseParkingLot(name);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }
        return "redirect:/";
    }
    @GetMapping("/parking/create/{name}")
    public String showFormCreateParking(@PathVariable String name, Model model){
        if (account.getRole().getId() == 1){
            ParkingLot parkingLot = parkingLotService.findByName(name);
            model.addAttribute("parking", parkingLot);
            model.addAttribute("account", account);

            List<String> availableParking = parkingLotService.getAvailableParking();
            model.addAttribute("arrayAvailableB1", availableParking.get(0));
            model.addAttribute("arrayAvailableB2", availableParking.get(1));

            return "parkinglot_create";
        }
        return "redirect:/";

    }
}
