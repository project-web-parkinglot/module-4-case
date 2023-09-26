package com.parkingcar.service.accountService;

import com.parkingcar.model.account.Account;
import com.parkingcar.repository.accountRepository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{
    @Autowired
    private IAccountRepository iAccountRepository;
    @Override
    public Account findAccountByUserName(String userName) {
        return iAccountRepository.findAccountByUserName(userName);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return iAccountRepository.findAccountByEmail(email);
    }

    @Override
    public void createAccount(Account account) {
        iAccountRepository.save(account);
    }
}
