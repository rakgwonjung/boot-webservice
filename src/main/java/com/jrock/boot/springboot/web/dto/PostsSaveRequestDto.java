package com.jrock.boot.springboot.web.dto;

import com.jrock.boot.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Entity 클래스와 비슷 하지만 절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.
// Dto는 Request와 Response용 View를 위한 클래스라서 자주 변경이 필요하다.
// View Layer와 DBB Layer의 역할 분리를 철저히 하는게 좋다
@Getter
@NoArgsConstructor
    public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {

        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
