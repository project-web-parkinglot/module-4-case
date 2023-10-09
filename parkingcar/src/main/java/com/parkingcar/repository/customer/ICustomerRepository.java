package com.parkingcar.repository.customer;

import com.parkingcar.dto.customer.CustomerDTO;
import com.parkingcar.dto.customer.ICustomerDTO;
import com.parkingcar.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
        Customer findCustomerByAccount_Id(int id);

        @Query(value = "select customer.id as customerId, customer.`name` as customerName, customer.room_rented " +
                "as roomRented, customer.phone_number as phoneNumber, parking_lot.`name` as parkingLotName, parking_lot.base_level " +
                "as parkingLotBase,bill.time_pay as timePay, bill.end_date as endDate,bill.id as billId, bill.money_pay as moneyPay, bill.`status` " +
                "as billStatus, car.license_plate as licensePlate,package_rent.package_name as namePackage from customer " +
                "join bill on bill.customer_id = customer.id join car on car.id = bill.car_id join parking_lot on parking_lot.id = bill.parking_lot_id " +
                "join package_rent on package_rent.id = bill.package_rent_id where customer.id =:id", nativeQuery = true)
        List<ICustomerDTO> findCustomerByBills(@Param("id") int id);

        @Query(value ="select customer.id as customerId, customer.`name` as customerName, customer.room_rented " +
                "as roomRented, customer.phone_number as phoneNumber, parking_lot.`name` as parkingLotName, parking_lot.base_level " +
                "as parkingLotBase,bill.time_pay as timePay, bill.end_date as endDate,bill.id as billId, bill.money_pay as moneyPay, bill.`status` " +
                "as billStatus, car.license_plate as licensePlate,package_rent.package_name as namePackage from customer " +
                "join bill on bill.customer_id = customer.id join car on car.id = bill.car_id join parking_lot on parking_lot.id = bill.parking_lot_id " +
                "join package_rent on package_rent.id = bill.package_rent_id where car.license_plate like :name ", nativeQuery = true)
        CustomerDTO searchCustomerByLicense(@Param("name") String name);
        
}
