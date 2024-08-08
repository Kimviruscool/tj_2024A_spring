package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {

    private int pno; //제품 번호
    private String ptitle; //제품명
    private String pcontent; //제품설명
    private int pprice; //제품가격
    private String pdate; //제품 등록일
    private int pview; //제품 조회수

    /////////// productimg table //////
    private int pimgno; //제품 이미지 번호
    private String pimgname; //제품 이미지 이름
    private String pimgdate; //제품 이미지 등록일

    //여러개 첨부파일 필드
    List<MultipartFile> files;
    // 여러개 파일명 필드
    List<String> fileNames;



}
