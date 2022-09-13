package com.nma.fashionblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String name;

    @NotNull
    private String email;
    private String role;

    @NotNull
    private String password;
}
