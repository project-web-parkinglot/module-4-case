package com.parkingcar.service.account;

import com.parkingcar.model.account.Account;
import com.parkingcar.repository.account.IAccountRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.security.crypto.bcrypt.BCrypt;

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
        String subject = "Confirm your email address! ParkingCar";
        // email form
        String verifyURL = siteURL + "/login/verify?code=" + account.getVerificationCode();
        String content = "<body>\n" +
                "<center style=\"width:100%;min-width:600px;margin:0 auto\">\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;margin:0 auto\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td align=\"center\">\n" +
                "                <div style=\"display:none;width:0px;height:0px;max-width:0px;max-height:0px;font-size:0px;line-height:0px\">\n" +
                "                    Agoda Login – Password reset instructions\n" +
                "                </div>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td align=\"center\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" style=\"padding:20px 15px 20px 15px\"><a href=\"http://localhost:8080/\"\n" +
                "                                                                                  title=\"C0523G1-Team4\" target=\"_blank\"><img\n" +
                "                                alt=\"C0523G1-Team4\" border=\"0\" height=\"36\"\n" +
                "                                src=\"/packing_lot_css/cover/cover2.png\"\n" +
                "                                width=\"80\" class=\"CToWUd\" data-bit=\"iit\"></a></td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;margin:0 auto\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td align=\"center\" valign=\"top\">\n" +
                "                <center style=\"width:100%;min-width:600px\">\n" +
                "                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td align=\"left\">\n" +
                "                                <table bgcolor=\"#ffffff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                       style=\"border:1px solid #efefef\" width=\"100%\">\n" +
                "                                    <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\" style=\"padding:20px 15px\">\n" +
                "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <h2 style=\"margin:0;font-size:18px;line-height:24px;font-family:'Helvetica',Arial,sans-serif;font-weight:normal;color:#f79d11\">\n" +
                "                                                            <br>\n" +
                "                                                            Hi " + account.getUsername() + "</h2>\n" +
                "\n" +
                "      <p >\n" +
                "        Welcome to C0523G1-Team 4's building, a modern and stylish residential complex that offers five spacious units.\n" +
                "        Situated in a prime location, our building provides a comfortable and convenient living experience for residents.\n" +
                "        To be a member of the building and having a parking space for your car,\n" +
                "        you must have a account to do it.\n" +
                "        Please click on the following link to confirm your email address: </p>" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" height=\"15\">&nbsp;</td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                               width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\">\n" +
                "                                                                    <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                           cellspacing=\"0\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                        <tr>\n" +
                "                                                                            <td align=\"center\" bgcolor=\"#0054a6\"\n" +
                "                                                                                style=\"border-radius:3px\"><a\n" +
                "                                                                                    href=\"" + verifyURL + "\"" +
                "                                                                                    style=\"font-size:14px;font-family:Helvetica,Arial,sans-serif;line-height:20px;font-weight:bold;color:#ffffff;text-decoration:none;border-radius:3px;padding:10px 40px;border:1px solid #0054a6;display:inline-block\"\n" +
                "                                                                                    target=\"_blank\">Verify your account</a></td>\n" +
                "                                                                        </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                    </table>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </center>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;margin:0 auto\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td align=\"center\" valign=\"top\">\n" +
                "                <center style=\"width:100%;min-width:600px\">\n" +
                "                    <p style=\"margin:0;padding:0;font-size:10px;line-height:10px\">&nbsp;</p>\n" +
                "\n" +
                "                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td align=\"left\">\n" +
                "                                <table bgcolor=\"#ffffff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                       style=\"border:1px solid #efefef\" width=\"100%\">\n" +
                "                                    <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\" style=\"padding:20px 15px\">\n" +
                "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <h2 style=\"margin:0;font-size:18px;line-height:24px;font-family:'Helvetica',Arial,sans-serif;font-weight:normal;color:#f79d11\">\n" +
                "                                                            Tired of remembering your password? Use the C0523G1-Team4 app!</h2>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" height=\"15\">&nbsp;</td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                                            <tbody>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" valign=\"top\">\n" +
                "                                                                    <p style=\"margin:0;font-size:14px;line-height:20px;font-family:'Helvetica',Arial,sans-serif;color:#666666\">\n" +
                "                                                                        • Let your smartphone remember and auto-fill\n" +
                "                                                                        your password</p>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" valign=\"top\">\n" +
                "                                                                    <p style=\"margin:0;font-size:14px;line-height:20px;font-family:'Helvetica',Arial,sans-serif;color:#666666\">\n" +
                "                                                                        • Fast-loading, easy to use, with even better\n" +
                "                                                                        deals and prices</p>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" valign=\"top\">\n" +
                "                                                                    <p style=\"margin:0;font-size:14px;line-height:20px;font-family:'Helvetica',Arial,sans-serif;color:#666666\">\n" +
                "                                                                        • Make and manage your bookings from anywhere,\n" +
                "                                                                        any time</p>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </center>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;margin:0 auto\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td align=\"center\" valign=\"top\">\n" +
                "                <center style=\"width:100%;min-width:600px\">\n" +
                "                    <p style=\"margin:0;padding:0;font-size:10px;line-height:10px\">&nbsp;</p>\n" +
                "\n" +
                "                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td align=\"left\">\n" +
                "                                <table bgcolor=\"#ffffff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                       style=\"border:1px solid #efefef\" width=\"100%\">\n" +
                "                                    <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\" style=\"padding:20px 15px\">\n" +
                "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <h2 style=\"margin:0;font-size:18px;line-height:24px;font-family:'Helvetica',Arial,sans-serif;font-weight:normal;color:#f79d11\">\n" +
                "                                                            Need to talk to us?</h2>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" height=\"15\">&nbsp;</td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" valign=\"top\">\n" +
                "                                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                           width=\"100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                        <tr>\n" +
                "                                                                            <td align=\"left\" valign=\"top\" width=\"211\">\n" +
                "                                                                                <p style=\"margin:0;font-size:14px;line-height:20px;font-weight:bold;font-family:'Helvetica',Arial,sans-serif;color:#333333\">\n" +
                "                                                                                    Building contact number:</p>\n" +
                "                                                                            </td>\n" +
                "                                                                            <td align=\"left\" valign=\"top\" width=\"357\">\n" +
                "                                                                                <p style=\"margin:0;font-size:14px;line-height:20px;font-family:'Helvetica',Arial,sans-serif;color:#666666\">\n" +
                "                                                                                    <a href=\"tel:+6626259106\"\n" +
                "                                                                                       style=\"color:#666666;text-decoration:none\"\n" +
                "                                                                                       target=\"_blank\">09351316457</a>\n" +
                "                                                                                    (VietNam)<br>\n" +
                "                                                                                </p>\n" +
                "                                                                            </td>\n" +
                "                                                                        </tr>\n" +
                "                                                                        <tr>\n" +
                "                                                                            <td align=\"left\" colspan=\"2\" height=\"0\"\n" +
                "                                                                                valign=\"top\">&nbsp;\n" +
                "                                                                            </td>\n" +
                "                                                                        </tr>\n" +
                "                                                                        <tr>\n" +
                "                                                                            <td align=\"left\" valign=\"top\" width=\"211\">\n" +
                "                                                                                <p style=\"margin:0;font-size:14px;line-height:20px;font-weight:bold;font-family:'Helvetica',Arial,sans-serif;color:#333333\">\n" +
                "                                                                                    Online customer support:</p>\n" +
                "                                                                            </td>\n" +
                "                                                                            <td align=\"left\" valign=\"top\" width=\"357\">\n" +
                "                                                                                <p style=\"margin:0;font-size:14px;line-height:20px;font-family:'Helvetica',Arial,sans-serif;color:#333333\">\n" +
                "                                                                                    <a href=\"http://localhost:8080/login/\"\n" +
                "                                                                                       style=\"color:#0283df;text-decoration:none\"\n" +
                "                                                                                       title=\"\" target=\"_blank\">C0523G1-Team4\n" +
                "                                                                                        <wbr>\n" +
                "                                                                                    </a></p>\n" +
                "                                                                            </td>\n" +
                "                                                                        </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                    </table>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </center>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;margin:0 auto\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td align=\"center\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" height=\"20\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" style=\"padding-bottom:15px;padding-left:5px;padding-right:5px\" valign=\"top\">\n" +
                "                            <p style=\"margin:0;margin-bottom:0;font-size:12px;line-height:18px;font-family:'Helvetica',Arial,sans-serif;color:#666666\">\n" +
                "                                This email was sent by C0523G1.<br>\n" +
                "                                280 Tran Hung Dao, CodeGym Da Nang, Viet Nam</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" height=\"15\" style=\"border-top:1px solid #dedede\" valign=\"top\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" valign=\"top\"><a href=\"http://localhost:8080/login/\" title=\"\" target=\"_blank\">\n" +
                "                            <img alt=\"C0523G1\" border=\"0\" height=\"36\"\n" +
                "                                src=\"/packing_lot_css/cover/cover2.png\"\n" +
                "                                width=\"80\" class=\"CToWUd\" data-bit=\"iit\"></a></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" height=\"40\" valign=\"top\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "</center>\n" +
                "</body>\n";
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
        if (account == null) {
            return false;
        } else {
            account.setVerificationCode(null);
            iAccountRepository.save(account);
            return true;
        }
    }

    @Override
    public void sendVerificationReset(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = account.getEmail();
        String fromAddress = "thien97.night1@gmail.com";
        String senderName = "ParkingCar!";
        String subject = "Change Password";
        String content = "<body>\n" +
                "<center style=\"width:100%;min-width:600px;margin:0 auto\">\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;margin:0 auto\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td align=\"center\">\n" +
                "                <div style=\"display:none;width:0px;height:0px;max-width:0px;max-height:0px;font-size:0px;line-height:0px\">\n" +
                "                    Agoda Login – Password reset instructions\n" +
                "                </div>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td align=\"center\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" style=\"padding:20px 15px 20px 15px\"><a href=\"http://localhost:8080/\"\n" +
                "                                                                                  title=\"C0523G1-Team4\" target=\"_blank\"><img\n" +
                "                                alt=\"C0523G1-Team4\" border=\"0\" height=\"36\"\n" +
                "                                src=\"/packing_lot_css/cover/cover2.png\"\n" +
                "                                width=\"80\" class=\"CToWUd\" data-bit=\"iit\"></a></td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;margin:0 auto\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td align=\"center\" valign=\"top\">\n" +
                "                <center style=\"width:100%;min-width:600px\">\n" +
                "                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td align=\"left\">\n" +
                "                                <table bgcolor=\"#ffffff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                       style=\"border:1px solid #efefef\" width=\"100%\">\n" +
                "                                    <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\" style=\"padding:20px 15px\">\n" +
                "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <h2 style=\"margin:0;font-size:18px;line-height:24px;font-family:'Helvetica',Arial,sans-serif;font-weight:normal;color:#f79d11\">\n" +
                "                                                            <br>\n" +
                "                                                            Hi " + account.getUsername() + "</h2>\n" +
                "\n" +
                "                                                        <p style=\"margin:0;margin-top:15px;font-size:14px;line-height:20px;font-family:'Helvetica',Arial,sans-serif;color:#666666\">\n" +
                "                                                            Forgot your password? No problem. Just click the button\n" +
                "                                                            below to reset it</p>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" height=\"15\">&nbsp;</td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                               width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\">\n" +
                "                                                                    <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                           cellspacing=\"0\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                        <tr>\n";
        String verifyURL = siteURL + "/login/verify_reset?code=" + account.getVerificationCode();
        content += "<td align=\"center\" bgcolor=\"#0054a6\"\n" +
                "                                                                                style=\"border-radius:3px\"><a\n" +
                "                                                                                    href=\"" + verifyURL + "\"" +
                "                                                                                    style=\"font-size:14px;font-family:Helvetica,Arial,sans-serif;line-height:20px;font-weight:bold;color:#ffffff;text-decoration:none;border-radius:3px;padding:10px 40px;border:1px solid #0054a6;display:inline-block\"\n" +
                "                                                                                    target=\"_blank\">Reset my password</a></td>\n" +
                "                                                                        </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                    </table>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </center>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;margin:0 auto\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td align=\"center\" valign=\"top\">\n" +
                "                <center style=\"width:100%;min-width:600px\">\n" +
                "                    <p style=\"margin:0;padding:0;font-size:10px;line-height:10px\">&nbsp;</p>\n" +
                "\n" +
                "                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td align=\"left\">\n" +
                "                                <table bgcolor=\"#ffffff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                       style=\"border:1px solid #efefef\" width=\"100%\">\n" +
                "                                    <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\" style=\"padding:20px 15px\">\n" +
                "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <h2 style=\"margin:0;font-size:18px;line-height:24px;font-family:'Helvetica',Arial,sans-serif;font-weight:normal;color:#f79d11\">\n" +
                "                                                            Tired of remembering your password? Use the C0523G1-Team4 app!</h2>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" height=\"15\">&nbsp;</td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                                            <tbody>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" valign=\"top\">\n" +
                "                                                                    <p style=\"margin:0;font-size:14px;line-height:20px;font-family:'Helvetica',Arial,sans-serif;color:#666666\">\n" +
                "                                                                        • Let your smartphone remember and auto-fill\n" +
                "                                                                        your password</p>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" valign=\"top\">\n" +
                "                                                                    <p style=\"margin:0;font-size:14px;line-height:20px;font-family:'Helvetica',Arial,sans-serif;color:#666666\">\n" +
                "                                                                        • Fast-loading, easy to use, with even better\n" +
                "                                                                        deals and prices</p>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" valign=\"top\">\n" +
                "                                                                    <p style=\"margin:0;font-size:14px;line-height:20px;font-family:'Helvetica',Arial,sans-serif;color:#666666\">\n" +
                "                                                                        • Make and manage your bookings from anywhere,\n" +
                "                                                                        any time</p>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </center>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;margin:0 auto\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td align=\"center\" valign=\"top\">\n" +
                "                <center style=\"width:100%;min-width:600px\">\n" +
                "                    <p style=\"margin:0;padding:0;font-size:10px;line-height:10px\">&nbsp;</p>\n" +
                "\n" +
                "                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td align=\"left\">\n" +
                "                                <table bgcolor=\"#ffffff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                       style=\"border:1px solid #efefef\" width=\"100%\">\n" +
                "                                    <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\" style=\"padding:20px 15px\">\n" +
                "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <h2 style=\"margin:0;font-size:18px;line-height:24px;font-family:'Helvetica',Arial,sans-serif;font-weight:normal;color:#f79d11\">\n" +
                "                                                            Need to talk to us?</h2>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" height=\"15\">&nbsp;</td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" valign=\"top\">\n" +
                "                                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" valign=\"top\">\n" +
                "                                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                           width=\"100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                        <tr>\n" +
                "                                                                            <td align=\"left\" valign=\"top\" width=\"211\">\n" +
                "                                                                                <p style=\"margin:0;font-size:14px;line-height:20px;font-weight:bold;font-family:'Helvetica',Arial,sans-serif;color:#333333\">\n" +
                "                                                                                    Building contact number:</p>\n" +
                "                                                                            </td>\n" +
                "                                                                            <td align=\"left\" valign=\"top\" width=\"357\">\n" +
                "                                                                                <p style=\"margin:0;font-size:14px;line-height:20px;font-family:'Helvetica',Arial,sans-serif;color:#666666\">\n" +
                "                                                                                    <a href=\"tel:+6626259106\"\n" +
                "                                                                                       style=\"color:#666666;text-decoration:none\"\n" +
                "                                                                                       target=\"_blank\">09351316457</a>\n" +
                "                                                                                    (VietNam)<br>\n" +
                "                                                                                </p>\n" +
                "                                                                            </td>\n" +
                "                                                                        </tr>\n" +
                "                                                                        <tr>\n" +
                "                                                                            <td align=\"left\" colspan=\"2\" height=\"0\"\n" +
                "                                                                                valign=\"top\">&nbsp;\n" +
                "                                                                            </td>\n" +
                "                                                                        </tr>\n" +
                "                                                                        <tr>\n" +
                "                                                                            <td align=\"left\" valign=\"top\" width=\"211\">\n" +
                "                                                                                <p style=\"margin:0;font-size:14px;line-height:20px;font-weight:bold;font-family:'Helvetica',Arial,sans-serif;color:#333333\">\n" +
                "                                                                                    Online customer support:</p>\n" +
                "                                                                            </td>\n" +
                "                                                                            <td align=\"left\" valign=\"top\" width=\"357\">\n" +
                "                                                                                <p style=\"margin:0;font-size:14px;line-height:20px;font-family:'Helvetica',Arial,sans-serif;color:#333333\">\n" +
                "                                                                                    <a href=\"http://localhost:8080/login/\"\n" +
                "                                                                                       style=\"color:#0283df;text-decoration:none\"\n" +
                "                                                                                       title=\"\" target=\"_blank\">C0523G1-Team4\n" +
                "                                                                                        <wbr>\n" +
                "                                                                                    </a></p>\n" +
                "                                                                            </td>\n" +
                "                                                                        </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                    </table>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </center>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;margin:0 auto\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td align=\"center\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" height=\"20\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" style=\"padding-bottom:15px;padding-left:5px;padding-right:5px\" valign=\"top\">\n" +
                "                            <p style=\"margin:0;margin-bottom:0;font-size:12px;line-height:18px;font-family:'Helvetica',Arial,sans-serif;color:#666666\">\n" +
                "                                This email was sent by C0523G1.<br>\n" +
                "                                280 Tran Hung Dao, CodeGym Da Nang, Viet Nam</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" height=\"15\" style=\"border-top:1px solid #dedede\" valign=\"top\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" valign=\"top\"><a href=\"http://localhost:8080/login/\" title=\"\" target=\"_blank\">\n" +
                "                            <img alt=\"C0523G1\" border=\"0\" height=\"36\"\n" +
                "                                src=\"/packing_lot_css/cover/cover2.png\"\n" +
                "                                width=\"80\" class=\"CToWUd\" data-bit=\"iit\"></a></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" height=\"40\" valign=\"top\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "</center>\n" +
                "</body>\n";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Override
    public boolean verify(String code) {
        Account account = iAccountRepository.findByVerificationCode(code);
        Calendar cal = Calendar.getInstance();
        if (account == null ) {
            return false;
        } else {
            account.setVerificationCode(null);
            account.setStatus(true);
            iAccountRepository.save(account);
            return true;
        }
    }

    @Override
    public void resetPW(Account account, String newPassword) {
        Account accountUser = iAccountRepository.findAccountByEmail(account.getEmail());
        account.setPassword(newPassword);
        account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12)));
        accountUser.setPassword(account.getPassword());
        iAccountRepository.save(accountUser);

    }

    @Override
    public void reset(Account accountUser) {
        String randomCode = RandomString.make(64);
        accountUser.setVerificationCode(randomCode);
        iAccountRepository.save(accountUser);
    }
}
