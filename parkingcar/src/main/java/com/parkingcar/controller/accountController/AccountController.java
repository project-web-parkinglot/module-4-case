package com.parkingcar.controller.accountController;

import com.parkingcar.model.account.Account;
import com.parkingcar.service.accountService.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AccountController {

    @Autowired
    private IAccountService iAccountService;

    @GetMapping ("/create")
    public String loginForm(Account account, Model model){
        model.addAttribute("account", account);
        return "/account/login";
    }
    @PostMapping("/create")
    public String loginCreateAccount(Account account, Model model){
        model.addAttribute("message", "Create account successfully");
        return "/account/login";
    }
}
