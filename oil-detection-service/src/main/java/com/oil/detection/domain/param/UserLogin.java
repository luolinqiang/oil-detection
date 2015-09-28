package com.oil.detection.domain.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class UserLogin implements Serializable {

    private Long id;
    @NotNull(message = "{NotNull.UserLogin.userId}")
    @Pattern(regexp = "^1\\d{10}$", message = "{Phone.UserReg.userId}")
    private String userId;
    @NotNull(message = "{NotNull.UserLogin.password}")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}