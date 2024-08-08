package web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController //JSON 반환해야해서 RESTController 사용
@RequestMapping("/product") // 공통 mapping 지정
public class ProductController { //class start

    @PostMapping("/register")   //등록매핑
    public boolean pRegister(List<MultipartFile> files){ //@RequestParam("변수이름") : 값을 찾지 못할때 직접 지정해서 찾아야함
        System.out.println("files = " + files);
        return true;
    }

} //class end
