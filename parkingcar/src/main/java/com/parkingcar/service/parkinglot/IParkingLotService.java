package com.parkingcar.service.parkinglot;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.model.pakingLot.ParkingLot;

import java.util.List;

public interface IParkingLotService {
    List<ParkingLot> getWaitingCheckParkingLot();
    String convertClassJs(List<ParkingLot> list);
    String convertClassJsFull(List<ParkingLot> list);
    List<String> getBlockParking();
    List<String> getAvailableParking();
    List<String> getCheckParking();
    List<String> getMyParking(Account customerAccount);
    List<String> getAnonymousParking();
    ParkingLot findByName(String name);
    void lockParking(String name) throws IllegalAccessException;
    void unlockParking(String name) throws IllegalAccessException;
    void endLeaseParkingLot(String name) throws IllegalAccessException;
    Customer getCustomerByAccountId(Integer accountId);
}
