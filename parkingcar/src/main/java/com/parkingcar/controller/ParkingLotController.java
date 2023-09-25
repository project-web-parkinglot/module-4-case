package com.parkingcar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParkingLotController {
    @GetMapping("/")
    public String showParkingLotPage(){
        return "parkinglot";
    }
}
