package com.parkingcar.service.parkinglot;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.pakingLot.ParkingLot;
import com.parkingcar.model.pakingLot.ParkingLotStatus;
import com.parkingcar.repository.parkinglot.IParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class ParkingLotService implements IParkingLotService{
    @Autowired
    IParkingLotRepository parkingLotRepository;
    @Override
    public List<ParkingLot> findByOwn(Account account) {
        return parkingLotRepository.getParkingLotByCustomer_Account(account);
    }
    @Override
    public List<ParkingLot> getClosedParkingLot() {
        return parkingLotRepository.getClosedParkingLot();
    }

    @Override
    public List<ParkingLot> getAvailableParkingLot() {
        return parkingLotRepository.getAvailableParkingLot();
    }
    @Override
    public List<ParkingLot> getAdminCheckParkingLot() {
        return parkingLotRepository.getAdminCheckParkingLot();
    }
    public String convertClassJs(List<ParkingLot> list){
        String result = "[";
        for (ParkingLot parkingLot : list){
            result += "new parkingLot("
                    + parkingLot.getX1() + "," + parkingLot.getY1() + ","
                    + parkingLot.getX2() + "," + parkingLot.getY2() + ","
                    + parkingLot.getX3() + "," + parkingLot.getY3() + ","
                    + parkingLot.getX4() + "," + parkingLot.getY4() + ",'"
                    + parkingLot.getName() + "'),";
        }
        result += "]";
        return result;
    }
    @Override
    public String convertClassJsFull(List<ParkingLot> list){
        String result = "[";
//        for (ParkingLot parkingLot : list){
//            result += "new parkingLotMine("
//                    + parkingLot.getX1() + "," + parkingLot.getY1() + ","
//                    + parkingLot.getX2() + "," + parkingLot.getY2() + ","
//                    + parkingLot.getX3() + "," + parkingLot.getY3() + ","
//                    + parkingLot.getX4() + "," + parkingLot.getY4() + ",'"
//                    + parkingLot.getName() + "','" + parkingLot.getDueDate() + "','"
//                    + parkingLot.getCarImage() + "','" + parkingLot.getLicensePlate() + "','"
//                    + parkingLot.getCustomer().getName() + "','" + parkingLot.getCustomer().getDOB() + "','"
//                    + parkingLot.getCustomer().getAddress() + "','" + parkingLot.getCustomer().getGender() + "','"
//                    + parkingLot.getCustomer().getImages() + "','" + parkingLot.getCustomer().getPhoneNumber() + "','"
//                    + parkingLot.getCustomer().getRoomRented() + "'),";
//        }

        result += "]";
        return result;
    }

    @Override
    public List<String> getBlockParking() {
        List<String> result = new ArrayList<>();

        List<ParkingLot> blockParking = getClosedParkingLot();
        List<ParkingLot> blockParkingB1 = new ArrayList<>();
        List<ParkingLot> blockParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : blockParking){
            if (parkingLot.getBaseLevel() == 1){
                blockParkingB1.add(parkingLot);
            } else {
                blockParkingB2.add(parkingLot);
            }
        }
        result.add(convertClassJs(blockParkingB1));
        result.add(convertClassJs(blockParkingB2));

        return result;
    }

    @Override
    public List<String> getAvailableParking() {
        List<String> result = new ArrayList<>();

        List<ParkingLot> available = getAvailableParkingLot();
        List<ParkingLot> availableParkingB1 = new ArrayList<>();
        List<ParkingLot> availableParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : available){
            if (parkingLot.getBaseLevel() == 1){
                availableParkingB1.add(parkingLot);
            } else {
                availableParkingB2.add(parkingLot);
            }
        }
        result.add(convertClassJs(availableParkingB1));
        result.add(convertClassJs(availableParkingB2));

        return result;
    }

    @Override
    public List<String> getCheckParking() {
        List<String> result = new ArrayList<>();

        List<ParkingLot> own = getAdminCheckParkingLot();
        List<ParkingLot> ownParkingB1 = new ArrayList<>();
        List<ParkingLot> ownParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : own){
            if (parkingLot.getBaseLevel() == 1){
                ownParkingB1.add(parkingLot);
            } else {
                ownParkingB2.add(parkingLot);
            }
        }
        result.add(convertClassJsFull(ownParkingB1));
        result.add(convertClassJsFull(ownParkingB2));

        return result;
    }

    @Override
    public List<String> getMyParking(Account customerAccount) {
        List<String> result = new ArrayList<>();

        List<ParkingLot> own = findByOwn(customerAccount);
        List<ParkingLot> ownParkingB1 = new ArrayList<>();
        List<ParkingLot> ownParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : own){
            if (parkingLot.getBaseLevel() == 1){
                ownParkingB1.add(parkingLot);
            } else {
                ownParkingB2.add(parkingLot);
            }
        }
        List<ParkingLot> other = getAdminCheckParkingLot();
        for (int i = 0; i < own.size(); i++){
            other.remove(own.get(i));
        }
        List<ParkingLot> otherParkingB1 = new ArrayList<>();
        List<ParkingLot> otherParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : other){
            if (parkingLot.getBaseLevel() == 1){
                otherParkingB1.add(parkingLot);
            } else {
                otherParkingB2.add(parkingLot);
            }
        }

        result.add(convertClassJsFull(ownParkingB1));
        result.add(convertClassJsFull(ownParkingB2));
        result.add(convertClassJs(otherParkingB1));
        result.add(convertClassJs(otherParkingB2));

        return result;
    }

    @Override
    public List<String> getAnonymousParking() {
        List<String> result = new ArrayList<>();

        List<ParkingLot> other = getAdminCheckParkingLot();
        List<ParkingLot> otherParkingB1 = new ArrayList<>();
        List<ParkingLot> otherParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : other){
            if (parkingLot.getBaseLevel() == 1){
                otherParkingB1.add(parkingLot);
            } else {
                otherParkingB2.add(parkingLot);
            }
        }
        result.add(convertClassJs(otherParkingB1));
        result.add(convertClassJs(otherParkingB2));

        return result;
    }

    @Override
    public ParkingLot findByName(String name) {
        return parkingLotRepository.getParkingLotByNameIs(name);
    }

    @Override
    @Transactional
    public void lockParking(String name) throws IllegalAccessException {
        ParkingLot parkingLot = findByName(name);
//        if (parkingLot != null || parkingLot.getCar() != null) {
//            parkingLot.setParkingLotStatus(new ParkingLotStatus(1));
//            parkingLotRepository.save(parkingLot);
//        } else {
//            throw new IllegalAccessException("Cannot Lock this Parkinglot");
//        }
    }

    @Override
    @Transactional
    public void unlockParking(String name) throws IllegalAccessException {
        ParkingLot parkingLot = findByName(name);
//        if (parkingLot != null || parkingLot.getCar() != null) {
//            parkingLot.setParkingLotStatus(new ParkingLotStatus(2));
//            parkingLotRepository.save(parkingLot);
//        } else {
//            throw new IllegalAccessException("Cannot Unlock this Parkinglot");
//        }
    }

    @Override
    @Transactional
    public void endLeaseParkingLot(String name) throws IllegalAccessException {
        ParkingLot parkingLot = findByName(name);
//        if (parkingLot != null) {
//            parkingLot.setCar(null);
//            parkingLot.setParkingLotStatus(new ParkingLotStatus(2));
//            parkingLotRepository.save(parkingLot);
//        } else {
//            throw new IllegalAccessException("Cannot end lease this Parkinglot");
//        }
    }
}
