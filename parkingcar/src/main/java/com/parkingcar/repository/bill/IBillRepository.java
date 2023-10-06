package com.parkingcar.repository.bill;

import com.parkingcar.model.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBillRepository extends JpaRepository<Bill,Integer> {


}
