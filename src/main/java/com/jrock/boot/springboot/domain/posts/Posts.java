package com.jrock.boot.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Dto 클래스와 비슷 하지만 절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.
// Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스
// Entity 클래스를 기준으로 테이블이 생성되고, 스키마가 변경됩니다.
// View Layer와 DBB Layer의 역할 분리를 철저히 하는게 좋다
// Lombok
@Getter
// 기본생성자 자동추가
@NoArgsConstructor
// JPA 어노테이션 모델 선언
// DB의 테이블과 매칭될 클래스 ( Entity 클래스 )
@Entity
public class Posts {

    // PK
    @Id
    // PK 생성규칙, auto_incerement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 컬럼
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 컬럼 어노테이션이 없을 시 기본 값으로 생성 String 같은 경우는 varchar(255)
    private String author;

    // 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
