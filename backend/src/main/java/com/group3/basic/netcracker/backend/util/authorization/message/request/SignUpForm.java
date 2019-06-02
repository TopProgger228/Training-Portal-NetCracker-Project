package com.group3.basic.netcracker.backend.util.authorization.message.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignUpForm {
    @NotBlank
    private String username;
    @NotBlank
    private String fname;
    @NotBlank
    private String lname;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

    private Set<String> role;

    public String getUsername() {
        return username;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
