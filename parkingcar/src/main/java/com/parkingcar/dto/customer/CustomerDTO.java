package com.parkingcar.dto.customer;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.pakingLot.ParkingLot;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


public class CustomerDTO implements Validator {
    private int id;
    private String name;
    private String phoneNumber;
    private int gender;
    private String DOB;
    private String roomRented;
    private String address;
    private String images;
    private Account account;
    private List<Bill> bills;
    private List<ParkingLot> parkingLots;

    public CustomerDTO() {
    }

    public CustomerDTO(int id, String name, String phoneNumber, int gender, String DOB, String roomRented,
                       String address, String images, Account account, List<Bill> bills, List<ParkingLot> parkingLots)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.DOB = DOB;
        this.roomRented = roomRented;
        this.address = address;
        this.images = images;
        this.account = account;
        this.bills = bills;
        this.parkingLots = parkingLots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getRoomRented() {
        return roomRented;
    }

    public void setRoomRented(String roomRented) {
        this.roomRented = roomRented;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
