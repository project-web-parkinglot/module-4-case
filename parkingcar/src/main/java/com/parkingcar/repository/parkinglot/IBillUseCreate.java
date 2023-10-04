package com.parkingcar.repository.parkinglot;

import com.parkingcar.model.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillUseCreate extends JpaRepository<Bill, Integer> {
}
