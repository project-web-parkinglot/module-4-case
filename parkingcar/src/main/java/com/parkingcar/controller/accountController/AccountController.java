package com.parkingcar.controller.accountController;

import com.parkingcar.dto.account.AccountDto;
import com.parkingcar.model.account.Account;
import com.parkingcar.model.account.Role;
import com.parkingcar.model.customer.Customer;
import com.parkingcar.service.account.IAccountService;
import com.parkingcar.service.account.IRoleService;
import com.parkingcar.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.security.PublicKey;

@Controller

@RequestMapping("/login")
public class AccountController {

    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/")
    public String loginForm(Model model) {
        AccountDto accountDto = new AccountDto();
        model.addAttribute("accountDto", accountDto);
        return "/account/login";
    }

    @PostMapping("/")
    private String createAccount(@Validated @ModelAttribute("accountDto") AccountDto accountDto,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 HttpServletRequest request,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model)
            throws MessagingException, UnsupportedEncodingException {
        accountDto.setUserName(username);
        accountDto.setPassWord(password);
        new AccountDto().validate(accountDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("fail", "Wrong input, please check again");
            model.addAttribute("accountDto", accountDto);
            return "/account/login";
        }
        if (iAccountService.findAccountByEmail(accountDto.getEmail()) != null) {
            model.addAttribute("fail", "This email already exists!");
            return "/account/login";
        } else if (iAccountService.findAccountByUserName(accountDto.getUsername()) != null) {
            model.addAttribute("fail", "This username has been used, please take another one!");
            return "/account/login";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("fail", "Wrong input, please check again");
            model.addAttribute("accountDto", accountDto);
        }
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);
        account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12)));
        Role role = new Role();
        role.setId(2);
        account.setRole(role);
        System.out.println(account.getVerificationCode());
        iAccountService.createAccount(account);
        Customer customer = new Customer();
        customer.setAccount(account);
        customerService.saveCustomer(customer);
        String siteURL = getSiteURL(request);
        iAccountService.sendVerificationEmail(account, siteURL);
        redirectAttributes.addFlashAttribute("success", "please check your email to confirm your account! ");
        return "redirect:/login/";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model, Principal principal, RedirectAttributes redirectAttributes) {
        String userName = principal.getName();
        Account account = iAccountService.findAccountByUserName(userName);
        if (account.getRole().getName().equals("ROLE_CUSTOMER")) {
            if (!account.isStatus()) {
                return "account/login";
            } else {
                model.addAttribute("info", account);
                model.addAttribute("accountName", userName);
                return "/index";
            }
        } else if (account.getRole().getName().equals("ROLE_ADMIN")) {
            model.addAttribute("info", account);
            model.addAttribute("accountName", userName);
            return "/index";
        }
        return "/index";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/email")
    public String ShowChangePasswordPage(Model model) {
        model.addAttribute("accountDto", new AccountDto());
        return "/account/changePassword";
    }

    @PostMapping("/confirm_email")
    public String confirmEmail(@Validated AccountDto accountDto, @RequestParam("email") String email,
                               HttpServletRequest request, RedirectAttributes redirectAttributes,
                               Model model) throws UnsupportedEncodingException, MessagingException {
        if (iAccountService.findAccountByEmail(accountDto.getEmail()) == null) {
            model.addAttribute("fail", "This email don't exists or invalid email format!");
            System.out.println(accountDto.getEmail());
            return "/account/changePassword";
        }
        Account account = iAccountService.findAccountByEmail(email);
        iAccountService.reset(account);
        String siteURL = getSiteURL(request);
        iAccountService.sendVerificationReset(account, siteURL);
        redirectAttributes.addFlashAttribute("successPassword", "Please check your email to change your account's password.");
        return "redirect:/login/";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code, RedirectAttributes redirectAttributes) {
        if (iAccountService.verify(code)) {
            redirectAttributes.addFlashAttribute("createSuccessfully", "Congratulations, your account has been verified.");
            return "redirect:/login/";
        } else {
            redirectAttributes.addFlashAttribute("fail", "Sorry, we could not verify account. It might be verified, or verification code is incorrect.");
            return "redirect:/login/";
        }
    }

    @GetMapping("/verify_reset")
    public String verifyReset(@RequestParam("code") String code, Model model,
                              RedirectAttributes redirectAttributes) {
        String email = null;
        if (iAccountService.findByCode(code) != null) {
            Account account = iAccountService.findByCode(code);
            email = account.getEmail();
        }
        if (iAccountService.verify(code)) {
            Account account = iAccountService.findAccountByEmail(email);
            model.addAttribute("account", account);
            return "/account/resetPassword";
        } else {
            redirectAttributes.addFlashAttribute("fail", "Sorry, we could not verify account. It might be already verified, or verification code is incorrect.");
            return "redirect:/login/";
        }
    }

    @PostMapping("/verify_reset_password")
    public String changePassword(@ModelAttribute Account accountUser, @RequestParam("new_pw") String new_pw,
                                 RedirectAttributes redirectAttributes) {
        iAccountService.resetPW(accountUser, new_pw);
        redirectAttributes.addFlashAttribute("successPasswordChange", "Password change successful.");
        return "redirect:/login/";
    }

    @GetMapping("/404")
    public String change404(Model model) {
        return "/account/404";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutPage() {
        return "redirect:/";
    }
}