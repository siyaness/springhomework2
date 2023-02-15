package com.example.springblog2.service;

import com.example.springblog2.dto.PostRequestDto;
import com.example.springblog2.dto.PostResponseDto;
import com.example.springblog2.entity.Post;
import com.example.springblog2.entity.User;
import com.example.springblog2.jwt.JwtUtil;
import com.example.springblog2.repository.PostRepository;
import com.example.springblog2.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request){
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시글 작성가능
        if(token != null){
            if(jwtUtil.validateToken(token)){
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            }else{
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않음")
            );

            // 요청받은 DTO로 DB에 저장할 객체 만들기
            Post post = postRepository.saveAndFlush(new Post(requestDto, user.getUsername()));

            return new PostResponseDto(post);
        }else{
            return null;
        }
    }

    // 조회하기
    @Transactional
    public List<PostResponseDto> getPosts(){
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postResponseDto = new ArrayList<>();

        for(Post post : postList){
            postResponseDto.add(new PostResponseDto(post));
        }

        return postResponseDto;
    }

    @Transactional
    public PostResponseDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디 존재하지않음")
        );
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }


    //수정
    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto, HttpServletRequest request){

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null){
            if(jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            }else{
                throw new IllegalArgumentException("Token Error");
            }

            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디 존재하지않음")
            );

            post.update(requestDto);

            PostResponseDto postResponseDto = new PostResponseDto(post);
            return postResponseDto;

        }else{
            return null;
        }
    }

    //삭제
    @Transactional
    public String deletePost(Long id, PostRequestDto requestDto, HttpServletRequest request){

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null){
            if(jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            }else{
                throw new IllegalArgumentException("Token Error");
            }
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디 존재하지않음")
            );

            postRepository.deleteById(id);
            return "{"+"success:"+ "true}";
        }else{
            return null;
        }
    }


}
