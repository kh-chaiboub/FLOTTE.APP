package com.flotte.flotte.app.api.user.ui.model;


import lombok.Data;

@Data

public class LoginRequest {
    private String username;
    private String email;
    private String password;
}
