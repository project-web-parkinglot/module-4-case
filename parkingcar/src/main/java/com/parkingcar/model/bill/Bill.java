package com.parkingcar.model.bill;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time_pay",columnDefinition = "date not null")
    private LocalDate timePay;

    @Column(name = "money_pay",columnDefinition = "int not null")
    private double moneyPay;
}
