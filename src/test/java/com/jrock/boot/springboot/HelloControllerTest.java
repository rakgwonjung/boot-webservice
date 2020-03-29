package com.jrock.boot.springboot;

import com.jrock.boot.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
// 스프링 부트 테스트와 Junit 사이에 연결자 역할
@RunWith(SpringRunner.class)
// Web에 집중하는 어노테이션
// @Controller, @ControllerAdvice 등 사용가능
// @Service, @Component, @Repository 등은 사용 불가
// 컨트롤러만 사용하기 때문에 이 것 사용 @WebMvcTest
// JPA 에서는 작동하지 않는다.
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    // Web MVC 테스트 시작 점
    private MockMvc mvc;

    @Test
    public void hello_리턴() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 URL 요청 , 체이닝 지원
        mvc.perform(
                // get 요청 /hello
                get("/hello"))
                // 체이닝
                // mvc.perform 결과를 검증
                // isOk (200) 검증
                .andExpect(status().isOk())
//                .andExpect(status().isNotFound())
                // 응답본문 검증, hello 값 검증
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto_리턴() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                // param 값 셋팅 String만 허용
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                // 200 체크
                .andExpect(status().isOk())
                // JSON 응답 값을 필드별로 검증하는 메서드
                // $를 기준으로 필드명을 명시한다.
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}
