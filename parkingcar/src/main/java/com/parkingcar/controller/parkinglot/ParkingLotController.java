package com.parkingcar.controller.parkinglot;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.account.Role;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.model.pakingLot.ParkingLot;
import com.parkingcar.repository.accountRepository.IAccountRepository;
import com.parkingcar.service.accountService.IAccountService;
import com.parkingcar.service.customer.ICustomerService;
import com.parkingcar.service.parkinglot.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ParkingLotController {

    @Autowired
    private IParkingLotService parkingLotService;
    @Autowired
    private IAccountService accountService;




    //    data login giả
//    3 admin b@gmail.com
//    2 employee a@gmail.com
//    1 customer thien97.night1@gmail.com
    Account account = new Account(1, "test", "aaa", "a@gmail.com", 1, new Role(3, "amin"));


    @GetMapping("/")
    public String showParkingLotPage(Model model){

        int role;
        if (account == null){
            role = 0;
        } else {
            role = account.getRole().getId();
        }
        model.addAttribute("role", role);
        //check role là admin hay khách thì thêm list cá nhân

        List<ParkingLot> blockParking = parkingLotService.getClosedParkingLot();
        List<ParkingLot> blockParkingB1 = new ArrayList<>();
        List<ParkingLot> blockParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : blockParking){
            if (parkingLot.getBaseLevel() == 1){
                blockParkingB1.add(parkingLot);
            } else {
                blockParkingB2.add(parkingLot);
            }
        }
        model.addAttribute("arrayBlockB1", convertClassJs(blockParkingB1));
        model.addAttribute("arrayBlockB2", convertClassJs(blockParkingB2));

        List<ParkingLot> available = parkingLotService.getAvailableParkingLot();
        List<ParkingLot> availableParkingB1 = new ArrayList<>();
        List<ParkingLot> availableParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : available){
            if (parkingLot.getBaseLevel() == 1){
                availableParkingB1.add(parkingLot);
            } else {
                availableParkingB2.add(parkingLot);
            }
        }
        model.addAttribute("arrayAvailableB1", convertClassJs(availableParkingB1));
        model.addAttribute("arrayAvailableB2", convertClassJs(availableParkingB2));

        if (account.getRole().getId() >= 2){
            List<ParkingLot> own = parkingLotService.getAdminCheckParkingLot();
            List<ParkingLot> ownParkingB1 = new ArrayList<>();
            List<ParkingLot> ownParkingB2 = new ArrayList<>();
            for (ParkingLot parkingLot : own){
                if (parkingLot.getBaseLevel() == 1){
                    ownParkingB1.add(parkingLot);
                } else {
                    ownParkingB2.add(parkingLot);
                }
            }
            model.addAttribute("ownParkingB1", convertClassJsFull(ownParkingB1));
            model.addAttribute("ownParkingB2", convertClassJsFull(ownParkingB2));
        }

        //trường hợp khách

        return "parkinglot";
    }
    private String convertClassJs(List<ParkingLot> list){
        String result = "[";
        for (ParkingLot parkingLot : list){
            result += "new parkingLot("
                    + parkingLot.getX1() + "," + parkingLot.getY1() + ","
                    + parkingLot.getX2() + "," + parkingLot.getY2() + ","
                    + parkingLot.getX3() + "," + parkingLot.getY3() + ","
                    + parkingLot.getX4() + "," + parkingLot.getY4() + ",'"
                    + parkingLot.getName() + "'),";
        }
        result += "]";
        return result;
    }
    private String convertClassJsFull(List<ParkingLot> list){
        String result = "[";
        for (ParkingLot parkingLot : list){
            result += "new parkingLotMine("
                    + parkingLot.getX1() + "," + parkingLot.getY1() + ","
                    + parkingLot.getX2() + "," + parkingLot.getY2() + ","
                    + parkingLot.getX3() + "," + parkingLot.getY3() + ","
                    + parkingLot.getX4() + "," + parkingLot.getY4() + ",'"
                    + parkingLot.getName() + "','" + parkingLot.getDueDate() + "','"
                    + parkingLot.getCarImage() + "','" + parkingLot.getLicensePlate() + "','"
                    + parkingLot.getCustomer().getName() + "','" + parkingLot.getCustomer().getDOB() + "','"
                    + parkingLot.getCustomer().getAddress() + "','" + parkingLot.getCustomer().getGender() + "','"
                    + parkingLot.getCustomer().getImages() + "','" + parkingLot.getCustomer().getPhoneNumber() + "','"
                    + parkingLot.getCustomer().getRoomRented() + "'),";
        }

        result += "]";
        return result;
    }
}
