package com.flotte.flotte.app.api.user.ui.model;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class CreateUserResponse {

    private String firstname;

    private String lastname;

    private String email;

    private String userId;


}
