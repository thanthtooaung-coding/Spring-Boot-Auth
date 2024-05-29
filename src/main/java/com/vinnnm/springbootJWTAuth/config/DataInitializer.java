package com.vinnnm.springbootJWTAuth.config;

import com.vinnnm.springbootJWTAuth.service.RoleService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final RoleService roleService;

    @Override
    public void run(ApplicationArguments args) {
        try {
            roleService.initializeRoles(Arrays.asList("ADMIN", "USER"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
