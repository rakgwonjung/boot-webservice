package com.jrock.boot.springboot.config.auth.dto;

import com.jrock.boot.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// User 클래스를 세션에 저장하려고 하면 직렬화를 구현하지 않았다는 에러가 발생
// User 클래스는 Entity클래스이기 때문에 엔티티 클래스에는 언제 다른 엔티티와 관계가 형성 될지 모른다.
// @OneToMany, @ManyTomany등 자식 엔티티를 갖고 있다면 직렬화 대상에 자식들까지 포함되니
// 성능 이슈, 부수효과가 발생할 확률이 높다.
// 그래서 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이 운영 및 유지보수 때 도움 된다.
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
