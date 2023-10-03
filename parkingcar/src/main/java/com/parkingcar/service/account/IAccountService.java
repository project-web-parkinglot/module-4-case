package com.parkingcar.service.account;

import com.parkingcar.model.account.Account;


public interface IAccountService {

    Account findAccountByUserName(String userName);

    Account findAccountByEmail(String email);
    void createAccount(Account account);
}
