package com.parkingcar.repository.parkinglot;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.model.pakingLot.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IParkingLotRepository extends JpaRepository<ParkingLot, Integer> {
    List<ParkingLot> getParkingLotByCustomer_Account(Account account);
    @Query(value = "select * from case_study_module_4.parking_lot where status = 0", nativeQuery = true)
    List<ParkingLot> getClosedParkingLot();
    @Query(value = "select * from case_study_module_4.parking_lot where status = 1 and customer_id is null", nativeQuery = true)
    List<ParkingLot> getAvailableParkingLot();
    @Query(value = "select * from case_study_module_4.parking_lot where status = 1 and customer_id is not null", nativeQuery = true)
    List<ParkingLot> getNotAvailableParkingLot();
    @Query(value = "select * from case_study_module_4.parking_lot where status = 1 and customer_id is not null", nativeQuery = true)
    List<ParkingLot> getAdminCheckParkingLot();
    ParkingLot getParkingLotByNameIs(String name);
}
