package com.parkingcar.service.parkinglot;

import com.parkingcar.model.customer.Customer;
import com.parkingcar.model.pakingLot.ParkingLot;

import java.util.List;

public interface IParkingLotService {
    List<ParkingLot> getAllParkingLot();
    ParkingLot findById(int id);
    List<ParkingLot> findByOwn(Customer customer);
    void updateParkingLot(ParkingLot parkingLot);
    List<ParkingLot> getClosedParkingLot();
    List<ParkingLot> getAvailableParkingLot();
    List<ParkingLot> getNotAvailableParkingLot(int id);
    List<ParkingLot> getAdminCheckParkingLot();
}
