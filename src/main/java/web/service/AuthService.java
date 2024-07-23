package web.service;


import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service //스프링 컨테이너의 등록하고 빈(객체) 생성
public class AuthService {

    @Autowired HttpServletRequest request; //HTTP 요청 객체

public boolean authCode(String email){
    //1. 인증코드가 문자인 이유 : 앞자리에 0이 들어갈수도 있으니까.
    try {
    String authCode = "";

    //2. 난수 생성
    Random random = new Random();
        //random.nextInt(); //int type 난수 생성
        for (int i = 0; i < 6; i++) {
            authCode += random.nextInt(10);

        }
        System.out.println("authCode = " + authCode);
        //3. (선택) DB : 영구적인 데이터 VS JVM(스택,힙,메소드) VS 세션(웹서버 저장소 - 요청하는 클라이언트 브라우저마다 )
        //1. 서버 세션의 인증 코드를 저장
        request.getSession().setAttribute("authCode", authCode); //request 세션 불러오기
        //2. 서버 세션 객체 의 생명 주기(세션이 유지되는 시간 ) //초 기준
        request.getSession().setMaxInactiveInterval(10); // 해당 초 만큼의 세션 생명주기 설정
        //3. 이메일 전송
        emailsned(email, "000 홈페이지의 회원가입 인증 코드 요청", "인증코드 : " + authCode);
        return true;
    } catch (Exception e) {System.out.println(e);} return false;
}

public boolean authCheck(String authCodeInput){
    //1. 인증 번호 호출
    Object object = request.getSession().getAttribute("authCode");
    if(object != null){ //세션 객체의 인증번호가 존재하지 않으면
        String authCode = (String)object; //강제 타입 변환
        //2. 입력받은 인증 번호와 인증 번호 비교
        if(authCode.equals(authCodeInput)){
            return true; //동일하면 true
        }
    }
    return false;
}

@Autowired JavaMailSender javaMailSender;

//3. 이메일 전송 함수 , 매개변수 : 받는사람의 이메일  , 메일 제목 , 메일 내용
public void emailsned(String toEmail , String subject , String content) {
    try {
        //1. 메일 내용물들을 포맷/형식 을 맞추기 위해 MIME
        //1. Mime 객체 생성
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //2. 메일 내용 구성
        //new MimeMessageHelper(mime객체 , 첨부파일여부 , 인코딩타입(한글사용가능여부) )
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        //3. 메일 보내는 사람
        mimeMessageHelper.setFrom("sinsa9122@naver.com");
        //4. 받는 사람 메일
        mimeMessageHelper.setTo(toEmail);
        //5. 메일 제목
        mimeMessageHelper.setSubject(subject);
        //6. 메일 내용
        mimeMessageHelper.setText(content);
        //7. 메일 전송
        javaMailSender.send(mimeMessage); //mime 객체를 보내기

    } catch (Exception e) {System.out.println(e);
    }
}
} //class end
