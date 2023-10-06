package com.parkingcar.repository.parkinglot;

import com.parkingcar.model.pakingLot.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarImgRepository extends JpaRepository<CarImage, Integer> {
}
