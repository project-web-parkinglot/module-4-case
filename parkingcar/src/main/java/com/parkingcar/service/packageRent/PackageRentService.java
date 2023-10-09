package com.parkingcar.service.packageRent;

import com.parkingcar.model.packageRent.PackageRent;
import com.parkingcar.repository.packageRent.IPackageRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageRentService implements IPackageRentService {
    @Autowired
    private IPackageRentRepository packageRentRepository;

    @Override
    public List<PackageRent> findAll() {
        return packageRentRepository.findAll();
    }

    @Override
    public PackageRent findById(int id) {
        return packageRentRepository.findById(id).get();
    }
}
