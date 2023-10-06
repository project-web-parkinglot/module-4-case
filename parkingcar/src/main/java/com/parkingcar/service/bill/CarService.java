package com.parkingcar.service.bill;
import com.parkingcar.model.pakingLot.Car;
import com.parkingcar.repository.parkinglot.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService implements ICarService {
    @Autowired
    private ICarRepository carRepository;


    @Override
    public Car getCarById(int id) {
        return carRepository.findById(id).get();
    }
}
