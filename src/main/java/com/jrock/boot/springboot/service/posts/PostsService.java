package com.jrock.boot.springboot.service.posts;

import com.jrock.boot.springboot.domain.posts.Posts;
import com.jrock.boot.springboot.domain.posts.PostsRepository;
import com.jrock.boot.springboot.web.dto.PostListResponseDto;
import com.jrock.boot.springboot.web.dto.PostsResponseDto;
import com.jrock.boot.springboot.web.dto.PostsSaveRequestDto;
import com.jrock.boot.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 스프링 Bean 주입 방식에는 @Autowired, setter, 생성자가 있다.
// 이 때 생성자가 가장 권장한다
// @RequiredArgsConstructor 이 것이 final로 선언 된 것은 생성자로 만들어 준다.
@RequiredArgsConstructor
@Service
public class PostsService {

    // @Autowired 없이 final이므로 @RequiredArgsConstructor가 생성자로 생성해줌(Bean 주입)
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));

        // update 는 postsRepository를 통해 쿼리를 날리지 않는다. (build 도 없네)
        // 엔티티가 영속성 컨텍스트에 포함 되어 있기 때문이다.(JPA의 영속성 컨텍스트)
        // JPA의 엔티티 매니저가 활성화 상태
        // 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태
        // 이 상태에서 데이터의 값으 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
        // 즉 위의 findById를 통해 테이블의 영속성 컨텍스트가 살아 있다. 이를 더티체킹이라 한다.
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= "+ id));

        postsRepository.delete(posts);
    }
}
