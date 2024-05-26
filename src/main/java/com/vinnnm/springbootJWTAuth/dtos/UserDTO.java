package com.vinnnm.springbootJWTAuth.dtos;

import java.util.Date;
import java.util.Set;

import com.vinnnm.springbootJWTAuth.models.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private long id;
	private String fullName, email, password;
	private Date createAt, updatedAt;
	private Set<Role> roles;
	private boolean enabled;
}
