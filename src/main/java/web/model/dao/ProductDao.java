package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Component //dao 어노테이션 설정

public class ProductDao extends Dao {

    //파일 등록 함수
    public boolean pRegister(ProductDto productDto){
        try{
            String sql = "";
            System.out.println(productDto);

        } catch (Exception e) {System.out.println(e);}
        return true;
    }

}
