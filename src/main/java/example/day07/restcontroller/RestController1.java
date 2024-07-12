package example.day07.restcontroller;

//including RESTful : HTTP 기반의 자원을 제공하는 인터페이스 구축(CRUD)
    //SPRING WEB 아닌 환경 = SERVLET 클래스 직접 구현 , SERVLET 클래스 CONTROLLER
    //SPRING WEB 환경 = MVC2 3티어 제공하여 CONTROLLER 자동으로 서블릿 등록


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

//해당 클래스가 SPIRNG MVC에서 controller 클래스 임을 등록 , 스프링 컨테이너(전역 저장소) 등록 , 빈(객체) 등록
    //-제어 역전 (IOC) : 객체 관리를 개발자가 아닌 스프링이 해준다.
// (왜? : 여러 개바라자가 공통으로 사용할 객체는 1명이 관리하면 좋은데, 그 1명의 관리를 스프링이 대신 해주겠다.)
@Controller
public class RestController1 {
    //    @RequestMapping(value = "해당메소드할/매핑할HTTP 주소정의", method = RequestMethod.(HTTP메소드))
    // method : RequestMethod.HTTP메소드명 : GET , POST , PUT , DELETE
        //서버내 동일한 URL을 정의할수 없다. 하지만 HTTP메소드가 다를경우 동일한 주소 정의 가능하다.
    //[1] HTTP GET방식 //URL : http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1", method = RequestMethod.GET) //http://localhost:8080 ◀ 생략 /example/rest1 ◀ 이것만 입력
    public void getRest1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("RestController1.getRest1");
        //데이터 요청
        String key = req.getParameter("key");
        System.out.println("key = " + key);
        //데이터 응답
        resp.getWriter().print("[GET]Client Hi");
    }

    //[2] HTTP POST 방식 //URL : http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1" , method = RequestMethod.POST)
    public void postRest1(HttpServletRequest req, HttpServletResponse resp)throws IOException{    //일반 자바 메소드 위에
        System.out.println("RestController1.postRest1");
        //데이터 요청
        String key = req.getParameter("key");
        System.out.println("key = " + key);
        //데이터 응답
        resp.getWriter().print("[POST]Client Hi");
    }

    //[3] http PUT //URL : http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1" , method = RequestMethod.PUT)
    public void putRest1(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        System.out.println("RestController1.putRest1");
        //데이터 요청
        String key = req.getParameter("key");
        System.out.println("key = " + key);
        //데이터 응답
        resp.getWriter().print("[PUT]Client Hi");
    }

    //[4] HTTP DELETE //URL : http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1" , method = RequestMethod.DELETE)
    public void deleteRest1(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        System.out.println("RestController1.deleteRest1");
        //데이터 요청
        String key = req.getParameter("key");
        System.out.println("key = " + key);
        //데이터 응답
        resp.getWriter().print("[DELETE]Client Hi");
    }
}
