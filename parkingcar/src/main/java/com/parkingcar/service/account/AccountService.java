package com.parkingcar.service.account;

import com.parkingcar.model.account.Account;
import com.parkingcar.repository.account.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.UUID;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Account findAccountByUserName(String username) {
        return iAccountRepository.findAccountByUsername(username);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return iAccountRepository.findAccountByEmail(email);
    }

    @Override
    public void createAccount(Account account) {
        account.setVerificationCode(UUID.randomUUID().toString());
        System.out.println(account.getVerificationCode());
        account.setStatus(false);
        iAccountRepository.save(account);
    }

    @Override
    public void sendVerificationEmail(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = account.getEmail();
        String fromAddress = "thien97.night1@gmail.com";
        String senderName = "ParkingCar!";
        String subject = "Confirm your email address";
        String content = "";
        // email form
        String verifyURL = siteURL + "/verify?code=" + account.getVerificationCode();
        content += "";
        // email form
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Override
    public Account findByCode(String code) {
        Account account = iAccountRepository.findByVerificationCode(code);
        return account;
    }

    @Override
    public Boolean VerifyReset(String code) {
        Account account = iAccountRepository.findByVerificationCode(code);
        Calendar cal = Calendar.getInstance();
        if (account == null){
            return false;
        }else {
            account.setVerificationCode(null);
            iAccountRepository.save(account);
            return true;
        }
    }

    @Override
    public void sendVerificationReset(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException {

    }

    @Override
    public boolean verify(String code) {
        return false;
    }

    @Override
    public void resetPW(Account account, String newPassword) {

    }

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        Account account = accountService.findAccountByEmail("thien97.night1@gmail.com");
        System.out.println(account);
    }
}
