package com.vinnnm.springbootJWTAuth.service.implement;

import com.vinnnm.springbootJWTAuth.dtos.UserDTO;
import com.vinnnm.springbootJWTAuth.models.Role;
import com.vinnnm.springbootJWTAuth.models.User;
import com.vinnnm.springbootJWTAuth.repository.RoleRepository;
import com.vinnnm.springbootJWTAuth.repository.UserRepository;
import com.vinnnm.springbootJWTAuth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void register(UserDTO userDTO) {
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(true);

        Role role = roleRepository.findByName(userDTO.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Collections.singleton(role));

        userRepository.save(user);
    }
}
