/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 8:00 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.dtos;

import java.util.Date;
import java.util.Set;

import com.vinnnm.springbootJWTAuth.models.Role;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for User.
 */
@Getter
@Setter
public class UserDto {

    /**
     * The unique identifier for the user.
     */
    private Long id;

    /**
     * The full name of the user.
     */
    private String fullName;

    /**
     * The unique email of the user.
     * This is used for user identification.
     */
    private String email;

    /**
     * The password of the user.
     * This should be encrypted in storage.
     */
    private String password;

    /**
     * The role of the user as a string.
     * This could represent a single role or primary role.
     */
    private String role;

    /**
     * The date and time when this user was created.
     */
    private Date createAt;

    /**
     * The date and time when this user was last updated.
     */
    private Date updatedAt;

    /**
     * The roles assigned to this user.
     * This can represent multiple roles.
     */
    private Set<Role> roles;

    /**
     * Indicates whether the user is enabled.
     */
    private boolean enabled;
}
