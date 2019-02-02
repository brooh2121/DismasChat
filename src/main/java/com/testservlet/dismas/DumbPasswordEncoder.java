package com.testservlet.dismas;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Dmitry on 28.01.2019.
 */
public class DumbPasswordEncoder  implements PasswordEncoder{
    @Override
    public String encode(CharSequence rawPassword) {
        return String.format("secret: '%s'", rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
