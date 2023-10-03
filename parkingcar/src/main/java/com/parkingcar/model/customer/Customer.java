package com.parkingcar.model.customer;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.packageRent.PackageRent;
import com.parkingcar.model.pakingLot.Car;
import com.parkingcar.model.pakingLot.ParkingLot;
import com.sun.istack.NotNull;
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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;
    private int gender;
    private String DOB;
    private String roomRented;
    private String address;

    @Column(columnDefinition = "LONGTEXT", length = 65535)
    private String images;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Bill> bills;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Car> carList;


}

