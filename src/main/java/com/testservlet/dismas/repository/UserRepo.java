package com.testservlet.dismas.repository;

import com.testservlet.dismas.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dmitry on 24.11.2018.
 */
public interface UserRepo extends JpaRepository <User, Long> {

    User findByUsername(String username);

    User findByActivationCode(String code);
}
