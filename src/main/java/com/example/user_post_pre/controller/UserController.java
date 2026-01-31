package com.example.user_post_pre.controller;

import com.example.user_post_pre.domain.Post;
import com.example.user_post_pre.domain.User;
import com.example.user_post_pre.repository.PostRepository;
import com.example.user_post_pre.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final PostRepository postRepository; // 추가

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // 특정 유저의 정보 조회
//    @GetMapping("/{id}")
//    public User getUser(@PathVariable Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }

    // [미션 1] User 1의 모든 Post 가져오기
    // 경로: GET /users/1/posts
    @GetMapping("/{userId}/posts")
    public List<Post> getUserPosts(@PathVariable Long userId) {
        return postRepository.findByUserId(userId);
    }

}