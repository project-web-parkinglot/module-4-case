package com.parkingcar.repository.admin;

import com.parkingcar.model.pakingLot.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IParkingRepository extends JpaRepository<ParkingLot,Integer> {
    @Query(value = "select count(status_id) as num from parking_lot where status_id =:id",nativeQuery = true)
    int countByParkingLotStatus(@Param("id") int id);
}
