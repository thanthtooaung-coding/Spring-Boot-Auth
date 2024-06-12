/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 9:15 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.repository;

import com.vinnnm.springbootJWTAuth.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository interface for managing {@link Role} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Retrieves a {@link Role} entity by its name, including the associated users.
     *
     * @param name the name of the role.
     * @return an {@link Optional} containing the role if found, otherwise empty.
     */
    @Query("SELECT r FROM Role r LEFT JOIN FETCH r.users WHERE r.name = :name")
    Optional<Role> findByName(@Param("name") String name);
}