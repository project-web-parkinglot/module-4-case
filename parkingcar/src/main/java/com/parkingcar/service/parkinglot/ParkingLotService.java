package com.parkingcar.service.parkinglot;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.bill.Bill;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.model.packageRent.PackageRent;
import com.parkingcar.model.pakingLot.Car;
import com.parkingcar.model.pakingLot.CarImage;
import com.parkingcar.model.pakingLot.ParkingLot;
import com.parkingcar.model.pakingLot.ParkingLotStatus;
import com.parkingcar.repository.packageRent.IPackageRentRepository;
import com.parkingcar.repository.parkinglot.ICustomerUseCreate;
import com.parkingcar.repository.parkinglot.IParkingLotRepository;
import com.parkingcar.repository.parkinglot.IParkingLotStatusRepository;
import com.parkingcar.repository.parkinglot.*;
import com.parkingcar.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Autowired
    private ICarRepository carRepository;
    @Autowired
    private ICarImgRepository carImgRepository;
    @Autowired
    private IPackageRentRepository packageRentRepository;
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
                    + customer.getRoomRented() + "'," + parkingLot.getId() + ", new Date('"
                    + car.getEditTime() + "')),";
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


        List<ParkingLot> hold = parkingLotRepository.getParkingLotsByParkingLotStatusId(4);
        List<ParkingLot> holdParkingB1 = new ArrayList<>();
        List<ParkingLot> holdParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : hold){
            if (parkingLot.getBaseLevel() == 1){
                holdParkingB1.add(parkingLot);
            } else {
                holdParkingB2.add(parkingLot);
            }
        }

        result.add(convertClassJsFull(ownParkingB1));
        result.add(convertClassJsFull(ownParkingB2));
        result.add(convertClassJsFull(holdParkingB1));
        result.add(convertClassJsFull(holdParkingB2));


        return result;
    }

    @Override
    public List<String> getMyParking(Account customerAccount) {
        List<String> result = new ArrayList<>();
        List<ParkingLot> total = parkingLotRepository.getParkingLotsByBill_Customer_Account(customerAccount);
        List<ParkingLot> own = new ArrayList<>();
        List<ParkingLot> hold = new ArrayList<>();

        for (ParkingLot parkingLot : total){
            if (parkingLot.getParkingLotStatus().getId() == 3){
                own.add(parkingLot);
            } else {
                hold.add(parkingLot);
            }
        }


        List<ParkingLot> ownParkingB1 = new ArrayList<>();
        List<ParkingLot> ownParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : own){
            if (parkingLot.getBaseLevel() == 1){
                ownParkingB1.add(parkingLot);
            } else {
                ownParkingB2.add(parkingLot);
            }
        }


        List<ParkingLot> holdParkingB1 = new ArrayList<>();
        List<ParkingLot> holdParkingB2 = new ArrayList<>();
        for (ParkingLot parkingLot : hold){
            if (parkingLot.getBaseLevel() == 1){
                holdParkingB1.add(parkingLot);
            } else {
                holdParkingB2.add(parkingLot);
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
        result.add(convertClassJsFull(holdParkingB1));
        result.add(convertClassJsFull(holdParkingB2));

        return result;
    }

    @Override
    public List<String> getAnonymousParking() {
        List<String> result = new ArrayList<>();

        List<ParkingLot> other = parkingLotRepository.getParkingLotsByParkingLotStatusId(3);
        List<ParkingLot> hold = parkingLotRepository.getParkingLotsByParkingLotStatusId(4);
        other.addAll(hold);

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
        if (parkingLot != null && parkingLotStatus.getId() == 1) {
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
        if (parkingLot != null && parkingLotStatus.getId() == 2) {
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
            Bill bill = billUseCreate.getBillByParkingLot(parkingLot);

            bill.setParkingLot(null);
            bill.setCustomer(null);
            bill.setCar(null);
            bill.setPackageRent(null);
            billUseCreate.save(bill);

            parkingLot.setParkingLotStatus(parkingLotStatusRepository.getParkingLotStatusById(1));
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
    @Transactional
    public void createNewRequest(Account account, Integer parkingId, String linkimg, String licensePlate, Integer pack) {
        Customer customer = getCustomerByAccountId(account.getId());
        ParkingLot parkingLot = parkingLotRepository.getParkingLotById(parkingId);
        parkingLot.setParkingLotStatus(parkingLotStatusRepository.getParkingLotStatusById(4));

        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setCustomer(customer);
        Car newCar = carRepository.save(car);

        linkimg = linkimg.trim();
        String[] imgList = linkimg.split(" ");
        for (String link : imgList){
            CarImage carImage = new CarImage();
            carImage.setCar(newCar);
            carImage.setUrlImg(link);
            carImgRepository.save(carImage);
        }

        PackageRent packageRent = packageRentRepository.getReferenceById(pack);
        Bill newBill = new Bill();
        newBill.setStatus("0");
        newBill.setCar(car);
        newBill.setCustomer(customer);
        newBill.setParkingLot(parkingLot);
        newBill.setPackageRent(packageRent);
        billUseCreate.save(newBill);
    }
    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkDueDate() {
        List<Bill> billList = billUseCreate.getBillsByEndDateAfter(LocalDate.now());
        for (Bill bill : billList){
            bill.setParkingLot(null);
            bill.setCustomer(null);
            bill.setCar(null);
            bill.setPackageRent(null);
            billUseCreate.save(bill);

            ParkingLot parkingLot = bill.getParkingLot();
            if (parkingLot != null){
                parkingLot.setParkingLotStatus(parkingLotStatusRepository.getParkingLotStatusById(1));
                parkingLotRepository.save(parkingLot);
            }
        }
    }
    @Override
    public void editCarInfo(String parkingName, String newPlate, String linkNewImg, String linkDelImg) {
        ParkingLot parkingLot = findByName(parkingName);
        if (parkingLot != null){
            Car car = parkingLot.getBill().getCar();
            car.setLicensePlate(newPlate);
            car.setEditTime(LocalDateTime.now());
            carRepository.save(car);

            if (!linkNewImg.equals("")){
                linkNewImg = linkDelImg.trim();
                String[] newImg = linkNewImg.split(" ");
                for (String link : newImg){
                    CarImage carImage = new CarImage();
                    carImage.setUrlImg(link);
                    carImage.setCar(car);
                    carImgRepository.save(carImage);
                }
            }
            if (!linkDelImg.equals("")){
                linkDelImg = linkDelImg.trim();
                linkDelImg = linkDelImg.replace(" +", "");
                String[] delImg = linkDelImg.split(" ");
                for (String link : delImg){
                    CarImage carImage = carImgRepository.getCarImageByUrlImgEquals(link);
                    carImage.setCar(null);
                    carImgRepository.save(carImage);
                }
            }
        }
    }
    @Override
    public List<PackageRent> getPackage() {
        return packageRentRepository.findAll();
    }

}
