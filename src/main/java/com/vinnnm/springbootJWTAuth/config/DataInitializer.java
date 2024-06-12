/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 8:45 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.config;

import com.vinnnm.springbootJWTAuth.service.RoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Component for initializing data during application startup.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleService roleService;

    /**
     * Initializes roles in the system.
     *
     * This method is called after the bean has been constructed and is used to ensure
     * that the necessary roles ("ADMIN", "USER") are present in the system.
     *
     * @throws Exception if an error occurs during role initialization.
     */
    @PostConstruct
    public void init() throws Exception {
        roleService.initializeRoles(Arrays.asList("ADMIN", "USER"));
    }
}
