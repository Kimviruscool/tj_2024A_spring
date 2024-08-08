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

    private String ptitle; //제품명
    private String pcontent; //제품설명
    private int pprice; //제품가격

    //여러개 첨부파일 필드
    List<MultipartFile> files;
    // 여러개 파일명 필드
    List<String> fileNames;

}
