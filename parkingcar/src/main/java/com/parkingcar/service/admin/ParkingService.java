package com.parkingcar.service.admin;

import com.parkingcar.repository.admin.IParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService implements IParkingService {
    @Autowired
    private IParkingRepository parkingRepository;

    @Override
    public int countByParkingLotStatus(int id) {
        return parkingRepository.countByParkingLotStatus(id);
    }
}
