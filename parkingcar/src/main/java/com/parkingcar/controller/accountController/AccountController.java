package com.parkingcar.controller.accountController;

import com.parkingcar.dto.account.AccountDto;
import com.parkingcar.model.account.Account;
import com.parkingcar.service.account.IAccountService;
import com.parkingcar.service.account.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IRoleService iRoleService;

    @GetMapping ("/login")
    public String loginForm( Model model){
        model.addAttribute("accountDto", new AccountDto());
        return "/account/login";
    }
    @PostMapping("/login")
    public String loginCreateAccount(@Validated Account account, Model model){
        account.setStatus(false);
        model.addAttribute("message", "Create account successfully");
        return "/account/view";
    }
}
