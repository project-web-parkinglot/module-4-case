package com.parkingcar.dto.customer;

public interface ICustomerDTO {
    int getCustomerId();
    int getBillId();
    String getCustomerName();
    String getRoomRented();
    String getPhoneNumber();
    String getParkingLotName();
    int getParkingLotBase();
    String getTimePay();
    String getEndDate();
    String getMoneyPay();
    String getBillStatus();
    String getLicensePlate();
    String getNamePackage();
}
