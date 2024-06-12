/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 10:15 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vinnnm.springbootJWTAuth.models.User;
import com.vinnnm.springbootJWTAuth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class implementing the UserDetailsService interface to load user-specific data.
 * Retrieves user details from the UserRepository and constructs UserDetails objects.
 */
@Service
@RequiredArgsConstructor
public class OurUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads user-specific data by the given email.
     *
     * @param email the email address of the user.
     * @return a UserDetails object containing the user's details.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new LoginUserDetail(user.get());
    }
}
