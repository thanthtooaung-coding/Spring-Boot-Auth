/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 9:30 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.vinnnm.springbootJWTAuth.models.Role;
import com.vinnnm.springbootJWTAuth.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Custom UserDetails implementation representing the details of a logged-in user.
 * Implements the Spring Security UserDetails interface.
 */
public class LoginUserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final User user;

    /**
     * Constructs a new LoginUserDetail object with the provided User entity.
     *
     * @param user the user entity for which details are to be represented.
     */
    public LoginUserDetail(User user) {
        this.user = user;
    }

    /**
     * Retrieves the authorities granted to the user.
     * Each role assigned to the user is represented as a GrantedAuthority.
     *
     * @return a collection of granted authorities for the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * Retrieves the password used to authenticate the user.
     *
     * @return the user's password.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Retrieves the username used to authenticate the user.
     *
     * @return the user's email address.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return always returns true, indicating that the account never expires.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked or unlocked.
     *
     * @return always returns true, indicating that the account is never locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     *
     * @return always returns true, indicating that the credentials never expire.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return true if the user is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
