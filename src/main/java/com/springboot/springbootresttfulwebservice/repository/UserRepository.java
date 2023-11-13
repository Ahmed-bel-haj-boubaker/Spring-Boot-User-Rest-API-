package com.springboot.springbootresttfulwebservice.repository;

import com.springboot.springbootresttfulwebservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserById(Long idUser);

    Optional<User> findUserByEmail(String email);
}
