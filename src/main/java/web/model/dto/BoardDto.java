
package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BoardDto {
    private long bno ;          // 번호
    private String btitle;      // 제목
    private String bcontent;        // 내용
    //Html의 input type이 file일때 바이트로 매핑 할때 사용되는
    private MultipartFile bfile;       // 첨부파일
    private long bview ;        // 조회수
    private String bdate;       // 작성일
    // 카테고리
    private long bcno ;         // 카테고리 번호
    private String bcname ;         // 카테고리 이름
    // 회원
    private long no ;          // 작성자 번호
    private String id;          // 작성자 아이디

}
