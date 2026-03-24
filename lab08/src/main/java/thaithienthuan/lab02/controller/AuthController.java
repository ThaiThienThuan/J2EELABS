package thaithienthuan.lab02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import thaithienthuan.lab02.model.Account;
import thaithienthuan.lab02.service.AccountService;

@Controller
public class AuthController {

@Autowired
private AccountService accountService;

@GetMapping("/register")
public String registerForm(Model model){
    model.addAttribute("account", new Account());
    return "register";
}

@PostMapping("/register")
public String register(@ModelAttribute Account account){

    accountService.registerStudent(account);

    return "redirect:/login";
}

@GetMapping("/login")
public String loginForm(Model model){
    return "login";
}


}
