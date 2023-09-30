package com.parkingcar.repository.accountRepository;

import com.parkingcar.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
        Account findAccountByUserName(String userName);

        @Query(value = "select  * from account where email = :email ",nativeQuery = true)
        Account findAccountByEmail(@Param("email") String email);

}
