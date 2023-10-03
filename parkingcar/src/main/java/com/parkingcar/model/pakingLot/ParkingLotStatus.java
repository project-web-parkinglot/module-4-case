package com.parkingcar.model.pakingLot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParkingLotStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String statusName;
    @OneToMany(mappedBy = "parkingLotStatus")
    private List<ParkingLot> parkingLotList;

    public ParkingLotStatus(Integer id) {
        this.id = id;
    }
}
