package com.parkingcar.service.packageRent;

import com.parkingcar.model.packageRent.PackageRent;

import java.util.List;

public interface IPackageRentService {
    List<PackageRent> findAll();
    PackageRent findById(int id);
}
