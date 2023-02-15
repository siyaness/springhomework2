package com.example.springblog2.controller;

import com.example.springblog2.dto.LoginRequestDto;
import com.example.springblog2.dto.SignupRequestDto;
import com.example.springblog2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;


    // 테스트용 회원가입 페이지
    @GetMapping("/signup")
    public ModelAndView signupPage(){
        return new ModelAndView("signup");
    }

    // 테스트용 로그인 페이지
    @GetMapping("/login")
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }

    // 회원가입
    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return "redirect:/api/auth/login";
    }


    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }
}
