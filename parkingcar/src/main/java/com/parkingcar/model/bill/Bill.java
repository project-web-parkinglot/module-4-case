package com.parkingcar.model.bill;

import com.parkingcar.model.customer.Customer;
import com.parkingcar.model.packageRent.PackageRent;
import com.parkingcar.model.pakingLot.Car;
import com.parkingcar.model.pakingLot.ParkingLot;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time_pay",columnDefinition = "date not null")
    private LocalDate timePay;

    public Bill() {
    }

    @Column(name = "money_pay",columnDefinition = "int not null")
    private double moneyPay;

    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "package_rent_id", referencedColumnName = "id")
    private PackageRent packageRent;

    @ManyToOne
    @JoinColumn(name = "packing_lot_id", referencedColumnName = "id")
    private ParkingLot parkingLot;
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;


    public Bill(int id, LocalDate timePay, double moneyPay, int status, Customer customer, PackageRent packageRent, ParkingLot parkingLot, Car car) {
        this.id = id;
        this.timePay = timePay;
        this.moneyPay = moneyPay;
        this.status = status;
        this.customer = customer;
        this.packageRent = packageRent;
        this.parkingLot = parkingLot;
        this.car = car;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getTimePay() {
        return timePay;
    }

    public void setTimePay(LocalDate timePay) {
        this.timePay = timePay;
    }

    public double getMoneyPay() {
        return moneyPay;
    }

    public void setMoneyPay(double moneyPay) {
        this.moneyPay = moneyPay;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PackageRent getPackageRent() {
        return packageRent;
    }

    public void setPackageRent(PackageRent packageRent) {
        this.packageRent = packageRent;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
