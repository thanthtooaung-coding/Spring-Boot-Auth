package com.vinnnm.springbootJWTAuth.dtos;

import com.vinnnm.springbootJWTAuth.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleDTO {
    private Long id;
    private String name;
    private List<User> users;
}
