package com.group3.basic.netcracker.backend.util.authorization.security.services;

import com.group3.basic.netcracker.backend.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Override
    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
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
        return pass;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public int getId() {
        return id;
    }

    public int getManager_id(){
        return manager_id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }



    private String username;

    private String role;

    private String fname;

    private String lname;

    private String email;

    private String pass;

    private LocalDate created_at;

    private byte[] photo = null;

    private int id;

    private int manager_id;

    private Collection<? extends GrantedAuthority> authorities;


    public UserPrinciple(String username, String  role, String fname, String lname, String email, String pass, LocalDate created_at, byte[] photo, int manager_id, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.role = role;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = pass;
        this.created_at = created_at;
        this.photo = photo;
        this.manager_id = manager_id;
        this.authorities = authorities;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Objects.toString(user.getRole())));

        return new UserPrinciple(
                user.getUsername(),
                user.getRole(),
                user.getFname(),
                user.getLname(),
                user.getEmail(),
                user.getPass(),
                user.getCreated_at(),
                user.getPhoto(),
                user.getManager_id(),
                authorities
        );
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

}
