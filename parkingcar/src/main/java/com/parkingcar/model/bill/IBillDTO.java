package com.parkingcar.model.bill;

import java.time.LocalDate;

public interface IBillDTO {
    int getBillId();
    int getCustomerId();
    LocalDate getEndDate();
    int getMoneyPay();
    String getStatus();
    LocalDate getTimePay();
    String getLicensePlate();
    String getCustomerName();
    String getPackageName();
    String getPosition();

}
