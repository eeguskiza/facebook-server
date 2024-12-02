package com.deusto.facebookserver.repository;

import com.deusto.facebookserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
}
