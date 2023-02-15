package com.example.springblog2.entity;

import com.example.springblog2.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

//    @Column(nullable = false)
//    @JsonIgnore
//    private String password;


    public Post(String title, String contents){
        this.title = title;
//        this.author = author;
        this.contents = contents;
//        this.password = password;
    }

    public Post(PostRequestDto requestDto, String username){
        this.title = requestDto.getTitle();
        this.username = username;
        this.contents = requestDto.getContents();
//        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
//        this.author = requestDto.getAuthor();
        this.contents = requestDto.getContents();
//        this.password = requestDto.getPassword();
    }

}
