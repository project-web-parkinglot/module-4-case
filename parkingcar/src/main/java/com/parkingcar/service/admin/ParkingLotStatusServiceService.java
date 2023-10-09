package com.parkingcar.service.admin;

import com.parkingcar.model.pakingLot.ParkingLotStatus;
import com.parkingcar.repository.parkinglot.IParkingLotStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotStatusServiceService implements IParkingLotStatusService {

    @Autowired
    private IParkingLotStatusRepository parkingLotStatusRepository;
    @Override
    public ParkingLotStatus getParkingLotStatusById(int id) {
       return parkingLotStatusRepository.getParkingLotStatusById(id);
    }
}
