package com.parkingcar.repository.account;

import com.parkingcar.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
        Account findAccountByUsername(String userName);

        Account findAccountByEmail(String email);

}
