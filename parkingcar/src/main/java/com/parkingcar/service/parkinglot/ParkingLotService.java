package com.parkingcar.service.parkinglot;

import com.parkingcar.model.customer.Customer;
import com.parkingcar.model.pakingLot.ParkingLot;
import com.parkingcar.repository.parkinglot.IParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParkingLotService implements IParkingLotService{
    @Autowired
    IParkingLotRepository parkingLotRepository;
    @Override
    public List<ParkingLot> getAllParkingLot() {
        return parkingLotRepository.findAll();
    }

    @Override
    public ParkingLot findById(int id) {
        return parkingLotRepository.findById(id).get();
    }

    @Override
    public List<ParkingLot> findByOwn(Customer customer) {
        return parkingLotRepository.getParkingLotByCustomer(customer);
    }

    @Override
    public void updateParkingLot(ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);
    }

    @Override
    public List<ParkingLot> getClosedParkingLot() {
        return parkingLotRepository.getClosedParkingLot();
    }

    @Override
    public List<ParkingLot> getAvailableParkingLot() {
        return parkingLotRepository.getAvailableParkingLot();
    }

    @Override
    public List<ParkingLot> getNotAvailableParkingLot(int id) {
        return parkingLotRepository.getNotAvailableParkingLot();
    }

    @Override
    public List<ParkingLot> getAdminCheckParkingLot() {
        return parkingLotRepository.getAdminCheckParkingLot();
    }
}
