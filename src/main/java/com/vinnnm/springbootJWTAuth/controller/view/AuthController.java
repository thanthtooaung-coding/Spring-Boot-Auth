package com.vinnnm.springbootJWTAuth.controller.view;

import com.vinnnm.springbootJWTAuth.dtos.UserDTO;
import com.vinnnm.springbootJWTAuth.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vinnnm/auth")
public class AuthController {
    private final UserService userService;
	@GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("/register")
    public ModelAndView registerPage(UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("userDTO", new UserDTO());
        return modelAndView;
    }

    @PostMapping("/register")
    public String registerUser(UserDTO userDTO) {
        userService.register(userDTO);
        return "redirect:/vinnnm/auth/login";
    }
}
