package com.parkingcar.repository.accountRepository;

import com.parkingcar.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
        Account findAccountByUserName(String userName);

        Account findAccountByEmail(String email);

}
