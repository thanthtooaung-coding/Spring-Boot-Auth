/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 10:30 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.service.implement;

import com.vinnnm.springbootJWTAuth.dtos.UserDto;
import com.vinnnm.springbootJWTAuth.exception.EntityNotFoundException;
import com.vinnnm.springbootJWTAuth.models.Role;
import com.vinnnm.springbootJWTAuth.models.User;
import com.vinnnm.springbootJWTAuth.repository.RoleRepository;
import com.vinnnm.springbootJWTAuth.repository.UserRepository;
import com.vinnnm.springbootJWTAuth.service.UserService;
import com.vinnnm.springbootJWTAuth.util.DtoUtil;
import com.vinnnm.springbootJWTAuth.util.EntityUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;

/**
 * Implementation of the {@link UserService} interface for managing users.
 * This service handles the registration of new users in the system.
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    /**
     * Registers a new user in the system. The user details are provided via a UserDto object.
     * The user's password is encoded, and the user is assigned a role.
     *
     * @param userDTO the data transfer object containing user registration details
     * @throws EntityNotFoundException if the specified role is not found
     * @throws EntityExistsException   if the user already exists in the role
     */
    @Override
    @Transactional
    public void register(UserDto userDTO) {
        User user = DtoUtil.map(userDTO, User.class, modelMapper);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName(userDTO.getRole())
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        if (role.getUsers().contains(user)) {
            throw new EntityExistsException("User already exists");
        }
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        EntityUtil.saveEntity(userRepository, user, "user");
    }
}
