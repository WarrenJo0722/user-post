package com.example.user_post_pre.controller;

import com.example.user_post_pre.domain.Post;
import com.example.user_post_pre.domain.User;
import com.example.user_post_pre.dto.PostCreateRequest;
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

//    @PostMapping
//    public Post createPost(@RequestBody Post post) {
//        return postRepository.save(post);
//    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest request) {
        // 1. 넘겨받은 userId로 실제 유저를 DB에서 찾는다.
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("작성자를 찾을 수 없습니다."));

        // 2. 새로운 Post 객체를 생성하고 유저를 세팅한다.
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setUser(user); // 이제 ID 대신 객체를 직접 넣는다.

        // 3. 저장
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
//    @GetMapping("/{postId}/author")
//    public User getPostAuthor(@PathVariable Long postId) {
//        return postRepository.findById(postId).orElse(null).getUser();
//    }

    @GetMapping("/{postId}/author")
    public User getPostAuthor(@PathVariable Long postId) {
        // 1. Post를 조회. 이때 연관된 User 정보도 함께 가져올 준비
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("포스트가 존재하지 않습니다."));

        // 2. post 객체에서 바로 user를 꺼내서 반환하면 끝!
        // 별도로 userRepository.findById()를 호출할 필요 없음
        return post.getUser();
    }

}
