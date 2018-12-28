package com.testservlet.dismas.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Dmitry on 24.11.2018.
 */
public enum Role implements GrantedAuthority {
    User, Admin;


        @Override
        public String getAuthority() {
            return name();
        }
}
