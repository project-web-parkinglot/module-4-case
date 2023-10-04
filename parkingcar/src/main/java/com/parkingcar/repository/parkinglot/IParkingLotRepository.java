package com.parkingcar.repository.parkinglot;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.pakingLot.ParkingLot;
import com.parkingcar.model.pakingLot.ParkingLotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IParkingLotRepository extends JpaRepository<ParkingLot, Integer> {

    List<ParkingLot> getParkingLotsByParkingLotStatusId(Integer parkingLotStatusId);

    ParkingLot getParkingLotByNameIs(String name);
//    @Query(value = "select bill.packing_lot_id " +
//                    "from bill join (select packing_lot_id, max(end_date) as max_end_date from bill " +
//            "group by packing_lot_id ) b2 on bill.packing_lot_id = b2.packing_lot_id and bill.end_date = b2.max_end_date " +
//            "where bill.customer_id = :id", nativeQuery = true)
//    List<Integer> getParkinglotIdByAccountId(@Param("id") Integer accountId);
    List<ParkingLot> getParkingLotsByBill_Customer_Account(Account account);

}
