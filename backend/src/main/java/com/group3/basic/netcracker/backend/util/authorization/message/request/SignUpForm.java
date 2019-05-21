package com.group3.basic.netcracker.backend.util.authorization.message.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Set;

import javax.validation.constraints.*;
import java.util.Set;

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

   // private byte[] photo;

    //private int id;

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

//    public int getId() {
//        return id;
//    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

//    public byte[] getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(Object photo) {
//        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
//             ObjectOutput out = new ObjectOutputStream(bos)) {
//            out.writeObject(photo);
//            this.photo = bos.toByteArray();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        //this.photo = (byte[])photo;
//    }
}
