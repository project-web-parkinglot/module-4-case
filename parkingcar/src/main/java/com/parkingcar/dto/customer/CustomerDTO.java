package com.parkingcar.dto.customer;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.packageRent.PackageRent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements Validator {
    private int id;
    private String name;
    private String phoneNumber;
    private String licensePlates;
    private LocalDate timeRemaining;
    private int quantitySlot;
    private String locationRent;
    private String roomRented;
    private String address;
    private String images;
    private int status;
    private PackageRent packageRent;
    private Account account;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
