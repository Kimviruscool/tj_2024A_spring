package example.day04.spring;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {
//===================================JSP==============================
    @RequestMapping("/")
    public @ResponseBody String root() throws Exception{
        return "JSP in Gradle";
    }

    @RequestMapping("/test1")       //localhost:8080/test1
    public String test1() {
        return "test1";             //실제 호출 될 /WEB-INF/views/test1.jsp
    }

    @RequestMapping("/test2")       //localhost:8080/test2
    public String test2() {
        return "sub/test2";             //실제 호출 될 /WEB-INF/views/sub/test2.jsp
    }
    //====================================================================
    @RequestMapping("/test3")
    public String test3(){
        return "test1.html";
    }
}
