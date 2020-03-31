package com.jrock.boot.springboot.domain.posts;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest     // 별다른 DB 설정이 없을 시 H2로 실행 된다.
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    // Junit 테스트 완료 후 실행
    @After
    public void cleanup() {
        // 모든 데이터 지움
        // 테스트 간 간섭이 일어 날 수 있으므로 끝나고 지워주자.
        postsRepository.deleteAll();
    }

    // 테스트 실행
    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // 저장 ID가 있으면 UPDATE 없으면 INSERT
        postsRepository.save(Posts.builder()
            .title(title)
            .content(content)
            .author("jrg8989@gmail.com")
            .build()
        );

        // when
        // 모든 목록 불러옴
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        // jUnit 것 이 아닌 assertj를 사용함.
        // 저장 데이터를 불러와 저장한 데이터와 비교 테스트
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2020,3,31,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate = " + posts.getCreatedDate() +
                ", modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
