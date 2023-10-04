package com.parkingcar.service.parkinglot;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.model.pakingLot.Car;
import com.parkingcar.model.pakingLot.CarImage;
import com.parkingcar.model.pakingLot.ParkingLot;
import com.parkingcar.model.pakingLot.ParkingLotStatus;
import com.parkingcar.repository.parkinglot.IBillUseCreate;
import com.parkingcar.repository.parkinglot.ICustomerUseCreate;
import com.parkingcar.repository.parkinglot.IParkingLotRepository;
import com.parkingcar.repository.parkinglot.IParkingLotStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class ParkingLotService implements IParkingLotService{
    @Autowired
    private IParkingLotRepository parkingLotRepository;
    @Autowired
    private IParkingLotStatusRepository parkingLotStatusRepository;
    @Autowired
    private ICustomerUseCreate customerUseCreate;
    @Autowired
    private IBillUseCreate billUseCreate;
    @Override
    public List<ParkingLot> getWaitingCheckParkingLot() {
        return parkingLotRepository.getParkingLotsByParkingLotStatusId(4);
    }

    public String convertClassJs(List<ParkingLot> list){
        String result = "[";
        for (ParkingLot parkingLot : list){
            result += "new parkingLot("
                    + parkingLot.getX1() + "," + parkingLot.getY1() + ","
                    + parkingLot.getX2() + "," + parkingLot.getY2() + ","
                    + parkingLot.getX3() + "," + parkingLot.getY3() + ","
                    + parkingLot.getX4() + "," + parkingLot.getY4() + ",'"
                    + parkingLot.getName() + "'," + parkingLot.getId() + "),";
        }
        result += "]";
        return result;
    }
    @Override
    public String convertClassJsFull(List<ParkingLot> list){
        String result = "[";
        for (ParkingLot parkingLot : list){
            Bill bill = parkingLot.getBill();
            Customer customer = bill.getCustomer();
            Car car = bill.getCar();
            List<CarImage> carImageList = car.getCarImageList();
            String dataImg = "[";
            for (CarImage carImage : carImageList){
                dataImg += ("'" + carImage.getUrlImg() + "',");
            }
            dataImg += "]";

            result += "new parkingLotMine("
                    + parkingLot.getX1() + "," + parkingLot.getY1() + ","
                    + parkingLot.getX2() + "," + parkingLot.getY2() + ","
                    + parkingLot.getX3() + "," + parkingLot.getY3() + ","
                    + parkingLot.getX4() + "," + parkingLot.getY4() + ",'"
                    + parkingLot.getName() + "','" + bill.getEndDate() + "',"
                    + dataImg + ",'" +car.getLicensePlate() + "','"
                    + customer.getName() + "','" + customer.getDOB() + "','"
                    + customer.getAddress() + "','" + customer.getGender() + "','"
                    + customer.getImages() + "','" + customer.getPhoneNumber() + "','"
                    + customer.getRoomRented() + "'," + parkingLot.getId() + "),";
        }
        result += "]";
        return result;
    }

    @Override
    public List<String> getBlockParking() {
        List<String> result = new ArrayList<>();

        List<ParkingLot> blockParking = parkingLotRepository.getParkingLotsByParkingLotStatusId(2);
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

        List<ParkingLot> available = parkingLotRepository.getParkingLotsByParkingLotStatusId(1);
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

        List<ParkingLot> own = parkingLotRepository.getParkingLotsByParkingLotStatusId(3);
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

        List<ParkingLot> own = parkingLotRepository.getParkingLotsByBill_Customer_Account(customerAccount);
        List<ParkingLot> ownParkingB1 = new ArrayList<>();
        List<ParkingLot> ownParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : own){
            if (parkingLot.getBaseLevel() == 1){
                ownParkingB1.add(parkingLot);
            } else {
                ownParkingB2.add(parkingLot);
            }
        }
        List<ParkingLot> other = parkingLotRepository.getParkingLotsByParkingLotStatusId(3);
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

        List<ParkingLot> other = parkingLotRepository.getParkingLotsByParkingLotStatusId(3);
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
        ParkingLotStatus parkingLotStatus = parkingLot.getParkingLotStatus();
        if (parkingLot != null || parkingLotStatus.getStatusName() == "Available") {
            parkingLot.setParkingLotStatus(parkingLotStatusRepository.getParkingLotStatusById(2));
            parkingLotRepository.save(parkingLot);
        } else {
            throw new IllegalAccessException("Cannot Lock this Parkinglot");
        }
    }

    @Override
    @Transactional
    public void unlockParking(String name) throws IllegalAccessException {
        ParkingLot parkingLot = findByName(name);
        ParkingLotStatus parkingLotStatus = parkingLot.getParkingLotStatus();
        if (parkingLot != null || parkingLotStatus.getStatusName() == "Blocked") {
            parkingLot.setParkingLotStatus(parkingLotStatusRepository.getParkingLotStatusById(1));
            parkingLotRepository.save(parkingLot);
        } else {
            throw new IllegalAccessException("Cannot Lock this Parkinglot");
        }
    }

    @Override
    @Transactional
    public void endLeaseParkingLot(String name) throws IllegalAccessException {
        ParkingLot parkingLot = findByName(name);
        if (parkingLot != null) {
            parkingLot.setParkingLotStatus(parkingLotStatusRepository.getParkingLotStatusById(1));
            parkingLot.setBill(null);
            parkingLotRepository.save(parkingLot);
        } else {
            throw new IllegalAccessException("Cannot end lease this Parkinglot");
        }
    }

    @Override
    public Customer getCustomerByAccountId(Integer accountId) {
        return customerUseCreate.getCustomerByAccount_Id(accountId);
    }

    @Override
    public ParkingLot getParkingById(Integer id) {
        return parkingLotRepository.getParkingLotById(id);
    }

    @Override
    public void saveNewBill(Bill bill) {
        billUseCreate.save(bill);
    }
}
