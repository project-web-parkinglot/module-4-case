package com.parkingcar.repository.parkinglot;

import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.pakingLot.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IBillUseCreate extends JpaRepository<Bill, Integer> {
    Bill getBillByParkingLot(ParkingLot parkingLot);
    List<Bill> getBillsByEndDateAfter(LocalDate timeNow);
}
