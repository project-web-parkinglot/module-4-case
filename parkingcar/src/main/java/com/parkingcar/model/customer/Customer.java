package com.parkingcar.model.Customer;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.packageRent.PackageRent;
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
    private String licensePlates;
    private LocalDate timeRemaining;
    private int quantitySlot;
    private String locationRent;
    private String roomRented;
    private String address;
    private String images;
    private int status;
    @ManyToOne()
    @JoinColumn(name = "packageRent_Id", referencedColumnName = "id")
    private PackageRent packageRent;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ParkingLot> parkingLots;
}
