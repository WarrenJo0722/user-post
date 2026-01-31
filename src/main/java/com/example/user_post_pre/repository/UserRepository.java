package com.example.user_post_pre.repository;

import com.example.user_post_pre.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}