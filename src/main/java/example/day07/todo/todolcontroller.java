package example.day07.todo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/todo")
public class todolcontroller {


    //[1]
    @GetMapping("/get")
    public boolean toDoGet(HttpServletRequest request){
        String key = request.getParameter("key");

        return todoDAO.getInstance().toDoGet(key);
    }
    //[2]
    @PostMapping("/post")
    public ArrayList<todoDto> toDoPost(HttpServletRequest request){

        return todoDAO.getInstance().toDoPost();
    }
}
