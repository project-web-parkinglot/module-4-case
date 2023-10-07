package com.parkingcar.repository.parkinglot;

import com.parkingcar.model.packageRent.PackageRent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParkingRentRepository extends JpaRepository<PackageRent, Integer> {
}
