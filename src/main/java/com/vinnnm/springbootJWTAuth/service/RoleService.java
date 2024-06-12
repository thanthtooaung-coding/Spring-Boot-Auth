/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 10:25 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.service;

import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service interface for role-related operations.
 */
@Service
public interface RoleService {

    /**
     * Initializes roles by creating them if they do not exist in the database.
     *
     * @param roles a list of role names to be initialized.
     * @throws Exception if an error occurs during the initialization process.
     */
    void initializeRoles(List<String> roles) throws Exception;
}
