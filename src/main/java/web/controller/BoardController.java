package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.service.BoardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board") //공통 URL : board 생성
public class BoardController {

    @Autowired
     BoardService boardService;

    //1.전체 카테고리 호출
    @GetMapping("/category")
    public List<Map<String,String>> bcFindAll(){
        System.out.println("BoardController.bcFindAll1");
        return boardService.bcFindAll();
    }

    //2. 게시물 작성 처리
    @PostMapping("/write")
    //테스트용
    //{
    //  "bcno" : 1 ,
    //  "btitle" : "안녕" ,
    //  "bcontent" : "하하하"
    //}
    public boolean bWrite(BoardDto boardDto){
        //문제 확인용 print
        System.out.println("BoardController.bWrite");
        System.out.println("boardDto = " + boardDto);

        return boardService.bWrite(boardDto);
    }

    //3. 글 전체 함수 호출
    @GetMapping("/all")
    public ArrayList<BoardDto> ball(BoardPageDto pageDto){
        // ---매개변수 : 
        //page : 페이징 처리에서 사용할 현 페이지
        //bcno : 현재 선택된 카테고리 번호
        //searchKey : 검색 조회시 사용되는 필드명
        //searchKeyword : 검색 조회시 사용되는 필드의 값
        System.out.println("BoardController.ball");
        return boardService.ball(pageDto);
    }

    //4. 글 상세 호출
    @GetMapping("/boardinfo")
    public BoardDto info(int bno){
        System.out.println("BoardController.info");
        return boardService.info(bno);
    }

    //글 수정

    //글 삭제
}
