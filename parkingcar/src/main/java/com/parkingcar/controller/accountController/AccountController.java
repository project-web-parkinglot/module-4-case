package com.parkingcar.controller.accountController;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.account.Role;
import com.parkingcar.service.accountService.IAccountService;
import com.parkingcar.service.accountService.IRoleService;
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

    @GetMapping("")
    public String showForm(){
        return "/account/view";
    }

    @GetMapping ("/create")
    public String loginForm(Account account, Model model){
        model.addAttribute("account", account);
        model.addAttribute("roleList", iRoleService.findAll());
        return "/account/login";
    }
    @PostMapping("/create")
    public String loginCreateAccount(@Validated Account account, Model model){
        account.setStatus(0);
        model.addAttribute("message", "Create account successfully");
        return "/account/view";
    }
}
