package com.example.gymcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gymcrm.model.User;
public interface UserDao extends JpaRepository<User, Long> {
}
