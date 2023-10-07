package com.parkingcar.model.pakingLot;

public class DataEditCar {
    private String parkingName;
    private String licensePlate;
    private String linkNewImg;
    private String linkDelImg;

    public DataEditCar() {
    }

    public DataEditCar(String parkingName, String licensePlate, String linkNewImg, String linkDelImg) {
        this.parkingName = parkingName;
        this.licensePlate = licensePlate;
        this.linkNewImg = linkNewImg;
        this.linkDelImg = linkDelImg;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLinkNewImg() {
        return linkNewImg;
    }

    public void setLinkNewImg(String linkNewImg) {
        this.linkNewImg = linkNewImg;
    }

    public String getLinkDelImg() {
        return linkDelImg;
    }

    public void setLinkDelImg(String linkDelImg) {
        this.linkDelImg = linkDelImg;
    }
}
