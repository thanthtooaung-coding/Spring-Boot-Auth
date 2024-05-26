package com.vinnnm.springbootJWTAuth.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "role")
@Getter
@Setter
public class Role implements Serializable {   
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String name;

	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private List<User> users;
	
	@Column(nullable = false, updatable = false)
    private long createdAt;

    @PrePersist
    protected void onCreate() {
    	this.createdAt = System.currentTimeMillis();
    }
}

