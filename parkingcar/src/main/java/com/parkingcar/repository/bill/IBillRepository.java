package com.parkingcar.repository.bill;

import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.bill.IBillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBillRepository extends JpaRepository<Bill,Integer> {
    @Query(value = "select bill.id as billId, bill.end_date as endDate, bill.money_pay as moneyPay, bill.status as status," +
            " bill.time_pay as timePay, car.license_plate as licensePlate,customer.id as customerId, customer.name as customerName," +
            " package_rent.package_name as packageName, parking_lot.name as position from bill " +
            "join car on bill.car_id = car.id join customer on customer.id = bill.customer_id " +
            "join package_rent on package_rent.id = bill.package_rent_id join parking_lot on parking_lot.id = bill.parking_lot_id " +
            "where bill.status =0",nativeQuery = true)
    List<IBillDTO> getAllByStatus();

    List<Bill> getBillByStatus(String status);
    @Query(value = "select count(`status`) from bill where `status` = 0",nativeQuery = true)
    int getCountStatus();
}
