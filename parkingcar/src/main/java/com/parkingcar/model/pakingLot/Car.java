package com.parkingcar.model.pakingLot;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.customer.Customer;
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
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String licensePlate;

    @OneToMany(mappedBy = "car")
    private List<CarImage> carImageList;


    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;
    @JsonBackReference
    @OneToMany(mappedBy = "car")
    private List<Bill> billList;
}
