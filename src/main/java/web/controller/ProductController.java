package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import web.model.dto.ProductDto;
import web.service.ProductService;

import java.util.List;

@RestController //JSON 반환해야해서 RESTController 사용
@RequestMapping("/product") // 공통 mapping 지정
public class ProductController { //class start

    @Autowired private ProductService productService;

    //상품등록 함수
    @PostMapping("/register")   //등록매핑
    public boolean pRegister(ProductDto productDto){ //@RequestParam("변수이름") : 값을 찾지 못할때 직접 지정해서 찾아야함
        return productService.pRegister(productDto);
    }

    //상품 목록 출력 함수
    @GetMapping("/productList")
    public List<ProductDto> getProductFindAll(){
        return productService.getProductFindAll();
    }

} //class end
