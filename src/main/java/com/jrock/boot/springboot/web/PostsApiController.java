package com.jrock.boot.springboot.web;

import com.jrock.boot.springboot.service.posts.PostsService;
import com.jrock.boot.springboot.web.dto.PostsResponseDto;
import com.jrock.boot.springboot.web.dto.PostsSaveRequestDto;
import com.jrock.boot.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// 스프링 Bean 주입 방식에는 @Autowired, setter, 생성자가 있다.
// 이 때 생성자가 가장 권장한다
// @RequiredArgsConstructor 이 것이 final로 선언 된 것은(, @NonNull도 붙여줌) 생성자로 만들어 준다.
// @AllArgsConstructor 는 파라미터로 받는 생성자 생성
// @NoArgsConstructor 는 파라미터가 없는 기본 생성자 생성
// @Data(생성자,게터세터,투스트링,이퀄등)는 다 만들어줌.
//
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    // @Autowired 없이 final이므로 @RequiredArgsConstructor가 생성자로 생성해줌(Bean 주입)
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {

        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
