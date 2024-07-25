package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.BoardDto;
import web.service.BoardService;

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
}
