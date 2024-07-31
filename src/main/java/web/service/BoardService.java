package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
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
        //파일 존재의 유효성검사
        if(boardDto.getUploadFile().isEmpty()){ //업로드 된 파일이 존재 하지 않으면
        }else {
            String uploadFileName = fileService.fileUpload(boardDto.getUploadFile());
            //1. 만약에 업로드가 실패 했으면 글쓰기 실패
            if(uploadFileName == null) return false;
            //2. BoardDto 에 업로드된 파일명 담아주기
            boardDto.setBfile(uploadFileName);
        }

        //--------------------- DB처리
        return boardDao.bWrite(boardDto);
    }
    
    //글 전체 호출함수
    public BoardPageDto ball(BoardPageDto pageDto){
        //만약에 페이지 번호가 매개변수로 존재하지 않으면 1페이지로  설정
        if(pageDto.getPage() ==0){pageDto.setPage(1);}
        //1. 하나의 페이지당 표시할 게시물수
        int pageboardSize = 5; //하나의 페이지당 5개씩 표시
        //2. 페이지당 게시물을 출력할 시작레코드 번호
        int starRow = (pageDto.getPage() - 1) * pageboardSize;

        // 4. 전체게시물수 : 조건추가) 카테고리번호 별 , 조건추가) 검색 조건
        int totalBoardSize = boardDao.getTotalBoardSize(
                pageDto.getBcno() ,
                pageDto.getSearchKey() , pageDto.getSearchKeyword() );

        // 3. totalPage : 전체 페이지수 구하기
        // 총 페이지수 계산식 : 전체게시물수 / 페이지당게시물수 , 몫:페이지수 , 나머지가 존재하면 페이지수 1 를 더한다.
                /* ex) 총 게시물수 : 23개 , 페이지당 5개씩 게시물 출력 , 총 페이지수 : 4페이지 +1 => 5페이지
                       총 게시물수 : 20개 , 페이지당 5개씩 게시물 출력 , 총 페이지수 : 4페이지
                       총 게시물수 : 15개 , 페이지당 10개씩 게시물 출력 , 총 페이지수 : 1페이지 +1 => 2페이지  */

        int totalPage = totalBoardSize % pageboardSize == 0 ? // 전체게시물수 나누기 페이지당게시물수 했을때 나머지가 없으면
                totalBoardSize / pageboardSize :      // 전체게시물수 나누기 페이지당게시물수 의 몫을 전체 페이지 수
                totalBoardSize / pageboardSize + 1;   // 나머지 게시물들을 출력할 페이지 1개 더해준다 , 전체 페이지 수 + 1

        // 5. 페이지별 시작 버튼 번호 , 끝 버튼 번호
            /* ex) 가정 : 총 페이지수가 13이고 페이지당 최대 버튼수를 5개씩 , 몫 활용해서 startBtn
                start 계산 식 : ( (현재페이지-1)/최대버튼수 ) * 최대버튼수 + 1
                end 계산 식 : start + 최대버튼수 -1  , 단 end 는 최대페이지수 보다 커질수 없다.
                                                           page        start       end      , page -1  , / 최대버튼수 , 몫 , *최대버튼수 , +1
                1페이지 일때 버튼 출력 : [1] [2] [3] [4] [5]     1           1           5           0       0 / 5        0      0           1
                2페이지 일때 버튼 출력 : [1] [2] [3] [4] [5]     2           1           5           1       1 / 5        0      0           1
                3페이지 일때 버튼 출력 : [1] [2] [3] [4] [5]     3           1           5           2       2 / 5        0      0           1
                4페이지 일때 버튼 출력 : [1] [2] [3] [4] [5]     4           1           5           3       3 / 5        0      0           1
                5페이지 일때 버튼 출력 : [1] [2] [3] [4] [5]     5           1           5           4       4 / 5        0      0           1
                6페이지 일때 버튼 출력 : [6] [7] [8] [9] [10]    6           6           10          5       5 / 5        1      5           6
                7페이지 일때 버튼 출력 : [6] [7] [8] [9] [10]    7           6           10          6       6 / 5        1      5           6
                8페이지 일때 버튼 출력 : [6] [7] [8] [9] [10]    8           6           10          7       7 / 5        1      5           6
                9페이지 일때 버튼 출력 : [6] [7] [8] [9] [10]    9           6           10          8       8 / 5        1      5           6
                10페이지 일때 버튼 출력 : [6] [7] [8] [9] [10]   10          6           10          9       9 / 5        1      5           6
                11페이지 일때 버튼 출력 : [11] [12] [13]         11          11          13          10      10 / 5       2      10          11
                12페이지 일때 버튼 출력 : [11] [12] [13]         12          11          13          11      11 / 5       2      10          11
                13페이지 일때 버튼 출력 : [11] [12] [13]         13          11          13          12      12 / 5       2      10          11
            */
        int btnSize = 5; // 페이지당 최대 버튼수를 5개씩 표기한다는 가정
        int startBtn = ( ( pageDto.getPage()-1) / btnSize ) * btnSize + 1; // 페이지별 시작 버튼 번호 변수
        int endBtn = startBtn + btnSize - 1; // 페이지별 끝 버튼 번호 변수
        if( endBtn >= totalPage ) endBtn = totalPage; // 만일 끝 번호가 마지막페이지 보다 커질수 없다.

        // 6. 게시물 정보 조회 : 조건추가1)페이징처리 , 조건추가2)카테고리별 , 조건추가3) 검색 조건
        List<BoardDto> data = boardDao.ball(
                starRow , pageboardSize ,
                pageDto.getBcno() ,
                pageDto.getSearchKey() , pageDto.getSearchKeyword() );

        // 7.반환 객체 구성
        BoardPageDto boardPageDto = BoardPageDto.builder()
                .page( pageDto.getPage() ) // 1. 현재 페이지 번호
                .totalBoardSize( totalBoardSize ) // 2. 전체 게시물수
                .totalPage( totalPage ) // 3. 전체 페이지수
                .data( data ) // 4. 조회된 게시물 정보 목록/리스트
                .startBtn( startBtn ) // 5. 페이지별 시작버튼 번호
                .endBtn( endBtn ) // 6. 페이지별 끝버튼 번호
                .build();
        return boardPageDto;
    }

    //글 상세 호출 함수
    public BoardDto info(int bno){
        boardDao.bviewIncrease(bno);
        return boardDao.info(bno);
    }
}
