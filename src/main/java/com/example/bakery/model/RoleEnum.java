package com.example.bakery.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    CLIENT, MANAGER, EMPLOYEE;

    @Override
    public String getAuthority() {
        return name();
    }
}
