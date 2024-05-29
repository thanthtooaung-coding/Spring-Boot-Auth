package com.vinnnm.springbootJWTAuth.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    void initializeRoles(List<String> roles) throws Exception;
}
