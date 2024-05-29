package com.vinnnm.springbootJWTAuth.service;

import com.vinnnm.springbootJWTAuth.dtos.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void register(UserDTO userDTO);
}
