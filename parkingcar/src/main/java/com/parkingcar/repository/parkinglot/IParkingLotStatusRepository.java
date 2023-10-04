package com.parkingcar.repository.parkinglot;

import com.parkingcar.model.pakingLot.ParkingLotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParkingLotStatusRepository extends JpaRepository<ParkingLotStatus, Integer> {
    ParkingLotStatus getParkingLotStatusById(Integer id);
}
