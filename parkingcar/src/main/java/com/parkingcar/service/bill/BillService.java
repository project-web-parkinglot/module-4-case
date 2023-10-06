package com.parkingcar.service.bill;

import com.parkingcar.model.bill.Bill;
import com.parkingcar.repository.bill.IBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService implements IBillService {

    @Autowired
    private IBillRepository billRepository;


    @Override
    public void saveBill(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public Bill findById(int id) {
        return billRepository.findById(id).get();
    }
}
