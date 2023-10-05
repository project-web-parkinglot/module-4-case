package com.parkingcar.repository.parkinglot;

import com.parkingcar.model.pakingLot.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarRepository extends JpaRepository<Car, Integer> {
}
