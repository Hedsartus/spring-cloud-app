package ru.filenko.gateway.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
