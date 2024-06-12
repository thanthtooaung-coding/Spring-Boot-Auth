/*
 * @Author : Thant Htoo Aung
 * @Date : 6/12/2024
 * @Time : 8:00 PM
 * @Project_Name : Spring Boot Auth
 */

package com.vinnnm.springbootJWTAuth.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entity representing a Role.
 */
@Entity
@Table(name = "role")
@Getter
@Setter
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the role.
     */
    private String name;

    /**
     * The list of users associated with this role.
     * This field is fetched eagerly and ignored in JSON serialization.
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users;

    /**
     * The date and time when this role was last updated.
     * This field is automatically managed by Hibernate.
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private Date createdAt;

}

