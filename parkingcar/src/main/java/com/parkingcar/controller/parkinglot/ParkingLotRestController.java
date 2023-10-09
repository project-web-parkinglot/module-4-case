package com.parkingcar.controller.parkinglot;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.pakingLot.DataEditCar;
import com.parkingcar.service.account.IAccountService;
import com.parkingcar.service.parkinglot.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parking/api")
public class ParkingLotRestController {
    @Autowired
    private IParkingLotService parkingLotService;
    @Autowired
            private IAccountService accountService;
    @GetMapping("/map")
    public ResponseEntity<List<String>> showParkingMap() {
        Account account = getAccount();
        int role;
        if (account == null) {
            role = 0;
        } else {
            role = account.getRole().getId();
        }
        List<String> reponseEntity = new ArrayList<>();

        List<String> blockParking = parkingLotService.getBlockParking();
        List<String> availableParking = parkingLotService.getAvailableParking();

        reponseEntity.add(blockParking.get(0)); //block 1
        reponseEntity.add(blockParking.get(1)); //block 2
        reponseEntity.add(availableParking.get(0)); //available 1
        reponseEntity.add(availableParking.get(1)); //available 2

        if (role == 1) {
            List<String> adminCheck = parkingLotService.getCheckParking();

            reponseEntity.add(adminCheck.get(0)); //own 1
            reponseEntity.add(adminCheck.get(1)); //own 2
            reponseEntity.add(adminCheck.get(2)); //waiting 1
            reponseEntity.add(adminCheck.get(3)); //waiting 2
            reponseEntity.add("[]"); //other1
            reponseEntity.add("[]"); //other2

        } else if (role == 2) {
            List<String> customerParking = parkingLotService.getMyParking(account);

            reponseEntity.add(customerParking.get(0)); //own 1
            reponseEntity.add(customerParking.get(1)); //own 2
            reponseEntity.add(customerParking.get(4)); //waiting 1
            reponseEntity.add(customerParking.get(5)); //waiting 2
            reponseEntity.add(customerParking.get(2)); //other 1
            reponseEntity.add(customerParking.get(3)); //other 2

        } else {
            List<String> anonymousParking = parkingLotService.getAnonymousParking();

            reponseEntity.add("[]"); //own 1
            reponseEntity.add("[]"); //own 2
            reponseEntity.add("[]"); //waiting 1
            reponseEntity.add("[]"); //waiting 2
            reponseEntity.add(anonymousParking.get(0)); //other 1
            reponseEntity.add(anonymousParking.get(1)); //other 2

        }
        return new ResponseEntity<>(reponseEntity, HttpStatus.OK);
    }
    @GetMapping("/lock/{name}")
    public ResponseEntity<?> lockParking(@PathVariable String name){
        Account account = getAccount();
        int role;
        if (account == null) {
            role = 0;
        } else {
            role = account.getRole().getId();
        }
        if (role == 1) {
            try {
                parkingLotService.lockParking(name);
            } catch (IllegalAccessException e) {
                System.out.println(e);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("/unlock/{name}")
    public ResponseEntity<?> unlockParking(@PathVariable String name) {
        Account account = getAccount();
        int role;
        if (account == null) {
            role = 0;
        } else {
            role = account.getRole().getId();
        }
        if (role == 1) {
            try {
                parkingLotService.unlockParking(name);
            } catch (IllegalAccessException e) {
                System.out.println(e);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("/endlease/{name}")
    public ResponseEntity<?> endLeaseParking(@PathVariable String name) {
        try {
            parkingLotService.endLeaseParkingLot(name);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/edit")
    public ResponseEntity<?> editCarInfo(@RequestBody DataEditCar dataEditCar){
        parkingLotService.editCarInfo(
                dataEditCar.getParkingName(),
                dataEditCar.getLicensePlate(),
                dataEditCar.getLinkNewImg(),
                dataEditCar.getLinkDelImg()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private Account getAccount(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Account account = accountService.findAccountByUserName(currentUserName);
            return account;
        }
        return null;
    }
}
