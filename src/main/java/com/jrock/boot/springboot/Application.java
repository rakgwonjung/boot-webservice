package com.jrock.boot.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA Auditing 활성화
// HELLO TEST 떄문에 임시 주석 JPA 인식 못함, config 패키지에 JpaConfig 추가
//@EnableJpaAuditing
// 스프링부트 자동설정, Bean 읽기 생성 자동설정, 특히 이 위치부터 읽기 시작한다, 항상 프로젝트 상단 배치
@SpringBootApplication
// 메인클래스
// H2 콘솔 http://localhost:8080/h2-console
public class Application {
    public static void main(String[] args) {
        // 이 것으로 인해 내장 WAS 를 실행한다
        // 톰캣을 설치할 필요 없고, 스프링 부트로 만들어진 Jar 파일로 실행
        SpringApplication.run(Application.class, args);
    }
}
