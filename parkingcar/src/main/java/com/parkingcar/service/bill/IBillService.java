package com.parkingcar.service.bill;

import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.bill.IBillDTO;
import com.parkingcar.repository.bill.IBillRepository;

import java.util.List;

public interface IBillService {
    void saveBill(Bill bill);
    Bill findById(int id);
    List<IBillDTO> getAllByStatus();
    List<Bill> getBillByStatus(String status);
    int getCountStatus();
}
