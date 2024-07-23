package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired MemberService memberService;

    // POST http://localhost:8080/member/signup?id=qwe&pw=asd&name=강호동&email=qwe@qwe&phone=123-123-123
    @PostMapping("/signup")
    public boolean mSignup( String id , String pw , String name , String email , String phone ){
//    public boolean mSignup( MemberDto memberDto ){
        System.out.println("MemberController.mSignup");
//        System.out.println("memberDto = " + memberDto);
        return memberService.mSignup( MemberDto.builder().id(id).pw(pw).name(name).email(email).phone(phone).build() );
    }

    // http://localhost:8080/member/login?id=qwe&pw=asd
    @PostMapping("/login")
    public boolean mLogin( MemberDto memberDto ){
        System.out.println("MemberController.mLogin");
        System.out.println("memberDto = " + memberDto);
        return memberService.mLogin( memberDto );
    }

    // 로그인 체크
    @GetMapping("/login/check")
    public MemberDto mLoginCheck( ){
        return memberService.mLoginCheck();
    }

    // 로그아웃
    @GetMapping("/logout")
    public void mLogout(){
        memberService.mLogout();
    }

    // 마이페이지 정보
    @GetMapping("/my/info")
    public MemberDto mMyInfo( ){ return memberService.mMyInfo(); }

    //중복 검사
    @GetMapping("/idcheck")
    public boolean mIDCheck(String id){
//        return memberService.mIDCheck(MemberDto.builder().id(id).build())}
        return memberService.mIDCheck(id);
    }

    //회원 탈퇴
    @DeleteMapping("/leave")
    public boolean mleave(String pwConfirm){
        return memberService.mleave(pwConfirm);
    }

    //회원 수정
    @PutMapping("/update")
    public boolean mupdate(String updateName, String nowPw, String updatePw, String updatePhone){
        return memberService.mupdate(updateName,nowPw,updatePw,updatePhone);
    }

    }











