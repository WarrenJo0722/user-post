package com.example.user_post_pre.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {
    private String title;
    private Long userId; // 클라이언트는 ID만 보내주면 된다.
}