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
    private String createAccount(@Validated  @ModelAttribute("accountDto") AccountDto accountDto,
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
        }if(bindingResult.hasErrors()){
            model.addAttribute("fail", "Wrong input, please check again");
            model.addAttribute("accountDto", accountDto);
        }
            Account account = new Account();
            BeanUtils.copyProperties(accountDto, account);
            account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12)));
            Role role = new Role();
            role.setId(1);
            account.setRole(role);
            System.out.println(account.getVerificationCode());
            iAccountService.createAccount(account);
            Customer customer = new Customer();
            customer.setAccount(account);
            customerService.saveCustomer(customer);
            String siteURL = getSiteURL(request);
            iAccountService.sendVerificationEmail(account,siteURL);
            redirectAttributes.addFlashAttribute("success","please check your email to confirm your account! ");
            redirectAttributes.addFlashAttribute("ok","ok");
        return "redirect:/login/create";
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
                return "/";
            }
        } else if (account.getRole().getName().equals("ROLE_ADMIN")) {
            model.addAttribute("info", account);
            model.addAttribute("accountName", userName);
            return "/";
        }
        return "/";
    }
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
