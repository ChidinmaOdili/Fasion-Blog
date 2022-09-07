package com.nma.fashionblog.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String role;
    private String password;
}
