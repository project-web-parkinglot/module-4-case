package com.parkingcar.dto.account;

import com.parkingcar.model.account.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AccountDto implements Validator {
    private int id;
    private String userName;
    private String passWord;
    private String email;
    private String name;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountDto accountDto = (AccountDto) target;
        if (!accountDto.name.matches("^[A-Z][a-z]+(\\s[A-Z][a-z]+)+$")){
            errors.rejectValue("name",null,"Ex: Truong Tan Hai");
        }
        if (!accountDto.name.matches("^\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b$")){
            errors.rejectValue("email",null,"Ex: Thang@gmail.com");
        }
        if (!accountDto.passWord.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$")){
            errors.rejectValue("passWord",null,"Password include at least 6 characters (contain letter and number)");
        }
    }
}
