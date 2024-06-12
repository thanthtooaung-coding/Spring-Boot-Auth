/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 8:00 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.dtos;

import com.vinnnm.springbootJWTAuth.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object for Role.
 */
@Getter
@Setter
public class RoleDto {

    /**
     * The unique identifier for the role.
     */
    private Long id;

    /**
     * The name of the role.
     * This defines the specific role such as ADMIN or USER.
     */
    private String name;

    /**
     * The list of users assigned to this role.
     * This represents the relationship between roles and users.
     */
    private List<User> users;
}
