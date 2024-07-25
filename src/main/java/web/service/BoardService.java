package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

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

        return boardDao.bWrite(boardDto);
    }
}
