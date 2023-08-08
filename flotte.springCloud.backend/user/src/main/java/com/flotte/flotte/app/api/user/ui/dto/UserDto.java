package com.flotte.flotte.app.api.user.ui.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String userId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String encryptPassword;
    private String profileImageUrl;
    private Date lastLoginDate;
    private Date joinDate;
    private String[] roles;//ROLE_USER{read,edit},ROLE_ADMIN{delete}
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;
}
