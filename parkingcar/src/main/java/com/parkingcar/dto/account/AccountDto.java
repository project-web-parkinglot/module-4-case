package com.parkingcar.dto.account;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.account.Role;
import com.parkingcar.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AccountDto implements Validator {

    private int id;
    private String username;
    private String password;
    private String email;
    private int status;
    private Role role;

    public AccountDto() {
    }

    public AccountDto(int id, String username, String password, String email, int status, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassWord(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountDto accountDto = (AccountDto) target;
        if (accountDto.getUsername().length() < 5) {
            errors.rejectValue("username", "", "Your UserName must be at least 6 characters or more!");
        }
        if (accountDto.email.trim().isEmpty()) {
            errors.rejectValue("email", "", "Email cannot is empty!");
        } else if (!accountDto.email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            errors.rejectValue("email", "", "Invalid email format!");
        }
        if (!accountDto.password.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$")) {
            errors.rejectValue("password", null, "Password include at least 6 characters (contain letter and number)");
        }
    }
}
