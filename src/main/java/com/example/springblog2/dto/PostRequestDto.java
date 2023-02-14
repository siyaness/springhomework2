package com.example.springblog2.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
//    private String author;
    private String contents;
//    private String password;

    public PostRequestDto(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
}

