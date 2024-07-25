package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service

public class BoardService {

    @Autowired
     BoardDao boardDao;

    //전체 카테고리 호출
    public List<Map<String,String>> bcFindAll(){
        System.out.println("BoardService.bcFindAll2");
        return boardDao.bcFindAll();
    }

    @Autowired MemberService memberService;
    @Autowired FileService fileService;

    //게시물 작성 처리
    public boolean bWrite(BoardDto boardDto){
        //작성을 요청한 회원의 로그인회원번호 호출
        //1. 로그인 세션에서 값 호출
        Object object = memberService.mLoginCheck();
        if (object == null) return false; //비로그인 시 뒤로 보내기
        //2. 세션내 회원번호 속성 호출
        MemberDto memberDto = (MemberDto)object;
        //3. 속성 호출
        int loginNo = memberDto.getNo();
        //4. BoardDto 에 담아주기
        boardDto.setNo(loginNo);
//================================================================
        //- 파일처리 확인 print
//        MultipartFile multipartFile = boardDto.getBfile();
//        System.out.println("multipartFile = " + multipartFile);
////        byte[] bytes = multipartFile.getBytes();
//        System.out.println(multipartFile.getContentType()); //파일 확장자
//        System.out.println(multipartFile.getName()); //속성명
//        System.out.println(multipartFile.getSize()); //파일의 바이트 사이즈(용량)
//        System.out.println(multipartFile.isEmpty()); //파일이 없으면 true 있으면 false
//========================================================================================
        //파일 업로드
        String uploadFileName = fileService.fileUpload(boardDto.getUploadFile());
        //1. 만약에 업로드가 실패 했으면 글쓰기 실패
        if(uploadFileName == null) return false;
        //2. BoardDto 에 업로드된 파일명 담아주기
        boardDto.setBfile(uploadFileName);
        //--------------------- DB처리

        return boardDao.bWrite(boardDto);
    }
}
