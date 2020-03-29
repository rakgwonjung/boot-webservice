package com.jrock.boot.springboot.web;

import com.jrock.boot.springboot.service.posts.PostsService;
import com.jrock.boot.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 스프링 Bean 주입 방식에는 @Autowired, setter, 생성자가 있다.
// 이 때 생성자가 가장 권장한다
// @RequiredArgsConstructor 이 것이 final로 선언 된 것은 생성자로 만들어 준다.
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    // @Autowired 없이 final이므로 @RequiredArgsConstructor가 생성자로 생성해줌(Bean 주입)
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {

        return postsService.save(requestDto);
    }
}
