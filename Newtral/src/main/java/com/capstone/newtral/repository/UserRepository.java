package com.capstone.newtral.repository;

import com.capstone.newtral.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //security 토큰 발급을 위한 쿼리문
    UserDetails getByUserId(String userId);

    Optional<User> findByUserId(String userId);

//    User findByUserId(String userId);

}
