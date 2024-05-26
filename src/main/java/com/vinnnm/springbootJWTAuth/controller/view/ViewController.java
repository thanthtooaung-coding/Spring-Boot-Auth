package com.vinnnm.springbootJWTAuth.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vinnnm")
public class ViewController {
	@GetMapping("/auth/login")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("/auth/register")
    public String registerPage() {
        return "register";
    }
}
