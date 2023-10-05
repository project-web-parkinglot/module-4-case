package com.parkingcar.model.pakingLot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLotStatus that = (ParkingLotStatus) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ParkingLotStatus{" +
                "id=" + id +
                ", statusName='" + statusName + '\'' +
                ", parkingLotList=" + parkingLotList +
                '}';
    }
}
