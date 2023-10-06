package com.parkingcar.repository.packageRent;

import com.parkingcar.model.packageRent.PackageRent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPackageRentRepository extends JpaRepository<PackageRent,Integer> {
}
