package example.day07.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller

public class RestController2 {
    //[1] HTTP GET
    @RequestMapping(value = "/example/rest2", method = RequestMethod.GET)
    @ResponseBody //응답할 데이터의 자바타입을 HTTP타입으로 자동 설정 : (java)String > (HTTP)text/plain;
    public String getRest2(HttpServletRequest request){
        System.out.println("RestController2.getRest2");

        //1. 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);

        //2. 응답
            //2-1 메소드의 반환 타입 정의한다.
        return "[GET]Client Hi";
            //2-2 @ResponseBody 메소드위에 정의한다.
    }

    //[2] HTTP POST
    @RequestMapping(value = "/example/rest2", method = RequestMethod.POST)
    @ResponseBody

    public ArrayList<RestDto> getPOst2(HttpServletRequest request){
        System.out.println("RestController2.getPOst2");

        //요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);

        //응답

//        [1] 배열 타입
//        String[] strArray = new String[2];
//        strArray[0] = "[POST]"; strArray[1] = "ClientHi";

//        [2] 리스트 타입
//        ArrayList<String> strArray = new ArrayList<>();
//        strArray.add("[POST]");
//        strArray.add("Client Hi");

        //[3] 객체 타입
//        RestDto strArray = new RestDto("[POST]", "Client1");

        //[4] 리스트 안에 DTO 타입
        ArrayList<RestDto> strArray = new ArrayList<>();
        strArray.add(new RestDto("[POST]", "Client Hi"));
        strArray.add(new RestDto("[POST]", "Client Hi"));


        return strArray;
    }

    // [3] HTTP PUT
    @RequestMapping(value = "/example/rest2" , method = RequestMethod.PUT)
    @ResponseBody
    public int putRest2(HttpServletRequest request){
        System.out.println("RestController2.putRest2");

        String key = request.getParameter("key");
        System.out.println("key = " + key);

        return 10+10; //application
    }

    //[4] HTTP DELETE
    @RequestMapping(value = "/example/rest2" , method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteRest2(HttpServletRequest request){
        System.out.println("RestController2.putRest2");

        String key = request.getParameter("key");
        System.out.println("key = " + key);

        return true;
    }
}
