/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 9:15 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vinnnm.springbootJWTAuth.models.User;

/**
 * Repository interface for managing {@link User} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a {@link User} entity by its email.
     *
     * @param email the email of the user.
     * @return an {@link Optional} containing the user if found, otherwise empty.
     */
    Optional<User> findByEmail(String email);
}
