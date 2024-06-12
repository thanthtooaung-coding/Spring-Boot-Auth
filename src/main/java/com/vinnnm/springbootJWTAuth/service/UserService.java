/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 10:20 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.service;

import com.vinnnm.springbootJWTAuth.dtos.UserDto;
import org.springframework.stereotype.Service;

/**
 * Service interface for user-related operations.
 */
@Service
public interface UserService {

    /**
     * Registers a new user.
     *
     * @param userDTO the DTO containing user data to register.
     */
    void register(UserDto userDTO);
}
