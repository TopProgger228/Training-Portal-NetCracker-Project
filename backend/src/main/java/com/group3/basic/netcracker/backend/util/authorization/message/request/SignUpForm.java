package com.group3.basic.netcracker.backend.util.authorization.message.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignUpForm {
    @NotBlank
    private String username;
   /* @NotBlank
    private long role_id;
    */

    @NotBlank
    private String fname;
    @NotBlank
    private String lname;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private int id;
    private Set<String> role;


   // private byte[] photo = null;

    public String getUsername() {
        return username;
    }

    /*public long getRole_id() {
        return role_id;
    }

     */

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

  /*  public byte[] getPhoto() {
        return photo;
    }

   */

    public int getId() {
        return id;
    }





    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
