package com.example.gymcrm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymcrm.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
