package com.example.user_post_pre.repository;

import com.example.user_post_pre.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // User 1의 모든 Post 가져오기
    List<Post> findByUserId(Long userId);
}
