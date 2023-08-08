package com.flotte.flotte.app.api.user.ui.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private String id;
    @Field("userId")
    private String userId;
    @Field("firstname")
    private String firstname;
    @Field("lastname")
    private String lastname;
    @Field("username")
    private String username;
    @Field("email")
    private String email;
    @Field("encryptPassword")
    private String encryptPassword;
    @Field("profileImageUrl")
    private String profileImageUrl;
    @Field("lastLoginDate")
    private Date lastLoginDate;
    @Field("joinDate")
    private Date joinDate;
    @Field("roles")
    private List<String> roles;//ROLE_USER{read,edit},ROLE_ADMIN{delete}
    @Field("authorities")
    private List<String> authorities;
    @Field("isActive")
    private boolean isActive;
    @Field("isNotLocked")
    private boolean isNotLocked;


}
