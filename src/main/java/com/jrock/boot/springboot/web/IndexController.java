package com.jrock.boot.springboot.web;

import com.jrock.boot.springboot.config.auth.LoginUser;
import com.jrock.boot.springboot.config.auth.dto.SessionUser;
import com.jrock.boot.springboot.service.posts.PostsService;
import com.jrock.boot.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
        // @LoginUser 어노테이션 사용 후 삭세
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");

        // 세션에 저장된 값이 있을 때만 model에 userName으로 등록
        // 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 된다.
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
