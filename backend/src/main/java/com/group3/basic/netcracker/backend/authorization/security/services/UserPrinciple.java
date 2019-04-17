package com.group3.basic.netcracker.backend.authorization.security.services;

import com.group3.basic.netcracker.backend.UsersTable.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Override
    public String getUsername() {
        return username;
    }

    public long getRole_id() {
        return role_id;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }



    private String username;

    private long role_id;

    private String fname;

    private String lname;

    private String email;

    private String pass;

    private LocalDate created_at;

    private byte[] photo = null;

    private int id;

    private Collection<? extends GrantedAuthority> authorities;


    public UserPrinciple(String username, long role_id, String fname, String lname, String email, String pass, LocalDate created_at, byte[] photo, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.role_id = role_id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = pass;
        this.created_at = created_at;
        this.photo = photo;
        this.authorities = authorities;
    }

    public static UserPrinciple build(Users user) {

        Set<Long> roles = new HashSet<>();
        roles.add(user.getRole_id());
        List<GrantedAuthority> authorities = roles.stream().map(role ->
                new SimpleGrantedAuthority(role.toString())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                user.getUsername(),
                user.getRole_id(),
                user.getFname(),
                user.getLname(),
                user.getEmail(),
                user.getPass(),
                user.getCreated_at(),
                user.getPhoto(),
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
