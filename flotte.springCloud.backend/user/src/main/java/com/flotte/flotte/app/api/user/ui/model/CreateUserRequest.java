package com.flotte.flotte.app.api.user.ui.model;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class CreateUserRequest {
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name charactters < 2")
    private String firstname;
    @NotNull(message = "lastname cannot be null")
    @Size(min = 2, message = "lastname charactters < 2")
    private String lastname;
    @Email
    private String email;
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 8, message = "2<password < 8")
    private String password;

}
