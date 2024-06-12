/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 8:30 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.controller.view;

import com.vinnnm.springbootJWTAuth.dtos.UserDto;
import com.vinnnm.springbootJWTAuth.exception.EntityCreationException;
import com.vinnnm.springbootJWTAuth.exception.EntityNotFoundException;
import com.vinnnm.springbootJWTAuth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for handling authentication-related requests.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/vinnnm/auth")
public class AuthController {
    private final UserService userService;

    /**
     * Displays the login page.
     *
     * @return the name of the login view template.
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Displays the registration page with a blank user data transfer object.
     *
     * @return a ModelAndView object with the registration view and a new UserDto.
     */
    @GetMapping("/register")
    public ModelAndView registerPage() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("userDTO", new UserDto());
        return modelAndView;
    }

    /**
     * Handles the registration of a new user.
     *
     * @param userDTO the data transfer object containing user details.
     * @param model   the model to pass attributes to the view.
     * @return the name of the view to be rendered.
     */
    @PostMapping("/register")
    public String registerUser(UserDto userDTO, Model model) {
        try {
            userService.register(userDTO);
            return "redirect:/vinnnm/auth/login";
        } catch (EntityCreationException | EntityNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
