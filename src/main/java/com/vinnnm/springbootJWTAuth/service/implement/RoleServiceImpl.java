/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 10:30 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.service.implement;

import com.vinnnm.springbootJWTAuth.models.Role;
import com.vinnnm.springbootJWTAuth.repository.RoleRepository;
import com.vinnnm.springbootJWTAuth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the {@link RoleService} interface for managing roles.
 * This service handles the initialization of roles in the system.
 */

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    private final RoleRepository roleRepository;

    /**
     * Initializes roles by checking if each role from the provided list exists,
     * and if not, it creates and saves the role.
     *
     * @param roles the list of role names to initialize
     * @throws Exception if there is an error while accessing the database or an unexpected error occurs
     */
    @Override
    @Transactional
    public void initializeRoles(List<String> roles) throws Exception {
        for (String roleName : roles) {
            try {
                if (roleRepository.findByName(roleName).isEmpty()) {
                    Role role = new Role();
                    role.setName(roleName);
                    roleRepository.save(role);
                    logger.info("Role {} has been inserted.", roleName);
                } else {
                    logger.info("Role {} already exists.", roleName);
                }
            } catch (DataAccessException e) {
                logger.error("Database access error while inserting role {}: {}", roleName, e.getMessage());
                throw new Exception("Error while accessing the database for role: " + roleName, e);
            } catch (Exception e) {
                logger.error("Unexpected error while inserting role {}: {}", roleName, e.getMessage());
                throw new Exception("Unexpected error for role: " + roleName, e);
            }
        }
    }
}