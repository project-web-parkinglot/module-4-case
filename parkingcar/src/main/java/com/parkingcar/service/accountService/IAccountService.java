package com.parkingcar.service.accountService;

import com.parkingcar.model.account.Account;
import org.springframework.stereotype.Service;


public interface IAccountService {

    Account findAccountByUserName(String userName);

    Account findAccountByEmail(String email);
    void createAccount(Account account);
}
