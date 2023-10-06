package com.parkingcar.service.bill;

import com.parkingcar.model.bill.Bill;

public interface IBillService {
    void saveBill(Bill bill);
    Bill findById(int id);
}
