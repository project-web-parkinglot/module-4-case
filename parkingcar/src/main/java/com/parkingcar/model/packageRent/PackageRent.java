package com.parkingcar.model.packageRent;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PackageRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String packageName;
    private int day;
    private double moneyRent;

    @OneToMany(mappedBy = "packageRent")
    List<Bill> bills;

}
