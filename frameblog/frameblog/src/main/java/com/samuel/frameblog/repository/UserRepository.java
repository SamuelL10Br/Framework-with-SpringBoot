package com.samuel.frameblog.repository;

import com.samuel.frameblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}