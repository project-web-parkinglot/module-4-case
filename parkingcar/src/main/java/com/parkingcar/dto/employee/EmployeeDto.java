package com.parkingcar.dto.employee;

import com.parkingcar.model.account.Account;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private int id;

    private String name;

    @NotNull
    private String dayOfBirth;

    private double salary;

    @NotNull
    private int gender;

    @NotNull
    private String image;

    private Account account;


}
