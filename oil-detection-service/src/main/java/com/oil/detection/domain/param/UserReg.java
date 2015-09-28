package com.oil.detection.domain.param;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class UserReg implements Serializable {

    private Long id;
    @NotNull(message = "{NotNull.UserReg.userId}")
    @Pattern(regexp = "^1\\d{10}$", message = "{Phone.UserReg.userId}")
    private String userId;
    @NotNull(message = "{NotNull.UserReg.realName}")
    private String realName;
    @NotNull(message = "{NotNull.UserReg.companyName}")
    private String companyName;
    @NotNull(message = "{NotNull.UserReg.password}")
    private String password;
    @NotBlank(message = "{NotNull.UserReg.confirmPassword}")
    private String confirmPassword;
    @NotBlank(message = "{NotNull.UserReg.verifyCode}")
    private String verifyCode;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}