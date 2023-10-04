package com.parkingcar.service.account;

import com.parkingcar.model.account.Account;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


public interface IAccountService {

    Account findAccountByUserName(String userName);

    Account findAccountByEmail(String email);
    void createAccount(Account account);

    void sendVerificationEmail(Account account,String siteURL) throws MessagingException, UnsupportedEncodingException;
    Account findByCode(String code);

    Boolean VerifyReset(String code);

    void sendVerificationReset(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException;

    boolean verify(String code);

    void resetPW(Account account, String newPassword);
}
