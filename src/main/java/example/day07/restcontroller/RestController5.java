package example.day07.restcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class RestController5 {

    //============= HTTP BODY 매개변수들을 매핑 방법 =====================//

    //[0] HTTP POST
    @PostMapping("/test5")
    public String teset5(String key1, int key2){
        System.out.println("RestController5.teset5");
        System.out.println("key1 = " + key1 + ", key2 = " + key2);
        return "test5 HI";
    }

    //[1] HTTP POST (BODY사용)
    @PostMapping("/test6")
//    public String test6(String key1, String key2){        //1번 실패 NULL
//    public String test6(RestDto restDto){                 //2번 실패 NULL
    public String test6(@RequestBody RestDto restDto){      //3번 성공
        System.out.println("RestController5.test6");

//        System.out.println("key1 = " + key1 + ", key2 = " + key2);        //1번 실패 NULL
//        System.out.println("restDto = " + restDto);                       //2번 실패 NULL
        System.out.println("restDto = " + restDto);                         //3번 성공

        return "test6 HI";
    }

}
