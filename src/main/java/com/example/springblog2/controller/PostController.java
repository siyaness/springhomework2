package com.example.springblog2.controller;

import com.example.springblog2.dto.PostRequestDto;
import com.example.springblog2.dto.PostResponseDto;
import com.example.springblog2.entity.Post;
import com.example.springblog2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }
    
    //글작성
    @PostMapping("/api/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.createPost(requestDto, request);
    }

    //전체조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    //아이디값으로 하나만 조회
    @GetMapping("/api/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    //수정
    @PutMapping("/api/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.update(id, requestDto, request);
    }

    //삭제
    @DeleteMapping("/api/posts/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.deletePost(id, requestDto, request);
    }
}
