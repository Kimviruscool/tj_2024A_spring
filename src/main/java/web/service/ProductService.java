package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.ProductDao;
import web.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Service //서비스 어노테이션

public class ProductService { //class start

    @Autowired private FileService fileService;
    @Autowired private ProductDao productDao;
    //1. 제품등록
    public boolean pRegister(ProductDto productDto){

        //- 여러개 첨부파일 업로드 하기
        List<String> fileNames = new ArrayList<>();
        // 1. 첨부파일 개수만큼 반복문을 돌린다.,일반포문사용 가능
        productDto.getFiles().forEach( file -> { //forEach in start
            //2. 각 첨부파일마다 하나씩 업로드 메소드에 대입한다.
            String fileName = fileService.fileUpload(file);
            if(fileName != null){ //if start
                //3. 업로드 된 파일명을 리스트에 담는다.
                //(업로드 된 파일명을 DB에 저장하기 위해서)
                fileNames.add(fileName);
            } //if end
        }  //forEach in end
      ); //forEach end
        productDto.setFileNames(fileNames);
        return productDao.pRegister(productDto);
    } //pRegister(method) end

    //상품 목록 출력 함수
    public List<ProductDto> getProductFindAll(){
        return productDao.getProductFindAll();
    }

} //class end
