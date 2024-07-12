package example.day07.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController //@Controller+@ResponseBody : 해당 클래스의 메소드들은 모두 @ResponseBody 적용
@RequestMapping(value = "/example") //컨트롤러 클래스 매핑 : 해당 클래스의 메소드들의 공통 URL 정의한다.

public class RestController3 {

    //[1] HTTP GET
    // @RequestMapping(value = "/example/rest3" , method = RequestMethod.GET)
    @GetMapping("/rest3") //GetMapping("HTTP URL정의") : GET방식의 HTTP GET메소드의 URL 정의
    public String getRest3(HttpServletRequest request){ //start
        System.out.println("RestController3.getRest3");
        //요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        //응답
        return "[GET]Client Hi";
    } //end

    //결과
    // type - text/plain
    // raw - [GET]Client Hi
//===============================================================================================================
    //[2] HTTP POST
    @PostMapping("/rest3") //PostMapping("HTTP URL정의") : Post방식의 HTTP Post메소드의 URL 정의
    public RestDto postRest4(HttpServletRequest request){
        System.out.println("RestController3.postRest4");
        //요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        //응답
        RestDto restDto = new RestDto("[POST]", "Client HI");
        return restDto;
    }

    //결과
    // type - Json
    // raw - {"key1":"[POST]","key2":"Client HI"}
//===============================================================================================================
    //[3] HTTP PUT
    @PutMapping("/rest3")
    public int putRest3(HttpServletRequest request){
        System.out.println("RestController3.putRest3");
        //요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        //응답
        return 10+10;
    }

    //결과
    // type - Json
    // raw - 20

//===============================================================================================================
    //[4] HTTP DELETE

    @DeleteMapping("/rest3")
    public boolean deleteRest3(HttpServletRequest request){
        System.out.println("RestController3.deleteRest3");
        //요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        //응답
        return true;
    }

    //결과
    // type - Json
    // raw - ture
//===============================================================================================================
}
