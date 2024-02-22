package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    private String name;
    private String lastname;
    private String nationality;
    private Date birthdate;
    private String email;
    private String password;
}
