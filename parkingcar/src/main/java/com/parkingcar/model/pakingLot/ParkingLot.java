package com.parkingcar.model.pakingLot;

import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate dueDate;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private int baseLevel;
    private int status;
    private String carImage;
    private String licensePlate;
    @ManyToOne()
    @JoinColumn(name = "customer_Id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<Bill> bills;
}
