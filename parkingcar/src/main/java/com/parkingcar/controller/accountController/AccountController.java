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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/")
public class AccountController {

    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("accountDto", new AccountDto());
        return "/account/login";
    }

    @PostMapping("/login")
    public String loginCreateAccount(@Validated Account account, Model model) {
        account.setStatus(false);
        model.addAttribute("message", "Create account successfully");
        return "/account/view";
    }

    @PostMapping("/signUp")
    private String createAccount(@Valid @ModelAttribute AccountDto accountDto, HttpServletRequest request,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model)
            throws MessagingException, UnsupportedEncodingException {
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
        } else {
            Account account = new Account();
            BeanUtils.copyProperties(accountDto, account);
            account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12)));
            Role role = new Role();
            role.setId(1);
            account.setRole(role);
            System.out.println(account.getVerificationCode());
            iAccountService.createAccount(account);
            Customer customer = new Customer();
//            customerService.
            String siteURL = getSiteURL(request);
//            iAccountService.
            redirectAttributes.addFlashAttribute("success","please check your email to confirm your account! ");
        }
        return "redirect:/login";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
