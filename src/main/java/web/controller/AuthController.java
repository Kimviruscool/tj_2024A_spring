package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.service.AuthService;

@RestController //@ResponseBody + controller (응답시 JSON 타입)
@RequestMapping("/auth") // 해당 클래스내 메소드들의 공통 URL

public class AuthController {

    @Autowired // 스프링 컨테이너의 빈(객체) 주입
    AuthService authService;

    //1. 인증 번호 요청
    @GetMapping("/code")
    public boolean authCode(){
        return authService.authCode();
    }

    //2. 입력받은 값과 인증 번호를 인증/비교
    @PostMapping("/check")
    public boolean authCheck(String authCodeInput){
        return authService.authCheck(authCodeInput);
    }
}
