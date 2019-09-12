package com.example.finalProject.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    Trader,
    Admin;

    @Override
    public String getAuthority() {
        return name();
    }
}
