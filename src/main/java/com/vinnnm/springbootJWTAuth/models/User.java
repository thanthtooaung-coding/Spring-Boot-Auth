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
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a User.
 */
@Table(name = "users")
@Entity
@Getter
@Setter
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    /**
     * The full name of the user.
     */
    @Column(nullable = false)
    private String fullName;

    /**
     * The unique email of the user.
     * The email must be unique and cannot be null.
     */
    @Column(unique = true, length = 100, nullable = false)
    private String email;

    /**
     * The password of the user.
     * The password cannot be null.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The date and time when this user was created.
     * This field is automatically managed by Hibernate.
     */
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createAt;

    /**
     * The date and time when this user was last updated.
     * This field is automatically managed by Hibernate.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * The roles assigned to this user.
     * This field is fetched eagerly and ignored in JSON serialization.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private Set<Role> roles;

    /**
     * Indicates whether the user is enabled.
     * This field cannot be null and is set to true upon creation.
     */
    @Column(nullable = false)
    private boolean enabled;

    /**
     * Sets the enabled field to true when the user is created.
     */
    @PrePersist
    protected void onCreate() {
        this.enabled = true;
    }

}
