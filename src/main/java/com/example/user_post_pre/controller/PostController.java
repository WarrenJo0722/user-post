package com.example.user_post_pre.controller;

import com.example.user_post_pre.domain.Post;
import com.example.user_post_pre.domain.User;
import com.example.user_post_pre.repository.PostRepository;
import com.example.user_post_pre.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository; // 추가

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    // 게시글 단건 조회
//    @GetMapping("/{id}")
//    public Post getPost(@PathVariable Long id) {
//        return postRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Post not found"));
//    }

    // [미션 2] Post 8을 작성한 User 정보 가져오기
    // 경로: GET /posts/8/author
    @GetMapping("/{postId}/author")
    public User getPostAuthor(@PathVariable Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("포스트가 존재하지 않습니다."));

        return userRepository.findById(post.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));
    }

}
