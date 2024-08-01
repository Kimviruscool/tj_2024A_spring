package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import web.model.dto.BoardDto;

import java.lang.reflect.Executable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BoardDao extends Dao{


    //1. 전체 카테고리 호출
    public List<Map<String,String>> bcFindAll(){
        System.out.println("BoardDao.bcFindAll3");
        //map 컬렉션 //map 컬렉션(객체)
        List<Map<String,String>> list = new ArrayList<>();

        try{
            String sql = "select * from bcategory";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                // map 컬렉션 엔트리 추가 (엔트리 : 키,값 집합)
                Map<String,String> map = new HashMap<>();

                // mno 컬렉션 엔트리 2개 추가 , 카테고리번호 , 카테고리 이름

                map.put("bcno",String.valueOf(rs.getInt(1) ) ); //정수 문자열 변경
                map.put("bcname",String.valueOf(rs.getString(2) ) );


                list.add(map);
            }
        }catch (Exception e){System.out.println(e);}
        return list;
    }

    //게시물 작성 처리
    public boolean bWrite(BoardDto boardDto){
        //확인용 print
        System.out.println("BoardDao.bWrite");
        System.out.println("boardDto = " + boardDto);
        try{
            String sql = "insert into board(bcno,btitle,bcontent,no,bfile)values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1,boardDto.getBcno());
            ps.setString(2, boardDto.getBtitle());
            ps.setString(3,boardDto.getBcontent());
            ps.setLong(4, boardDto.getNo());
            ps.setString(5,boardDto.getBfile());
            int count = ps.executeUpdate();
            if (count == 1) return true;

        } catch (Exception e){System.out.println(e);}
        return false;
    }

    // 3-2. 전체 게시물수 반환 처리 , 조건추가1) 카테고리 검색
    public int getTotalBoardSize( int bcno , String searchKey, String searchKeyword){
        try{ String sql ="select count(*) as 총게시물수 " +
                "from board inner join member " +
                "on board.no = member.no";

            // 카테고리가 존재하면 , 0 이면 : 카테고리가 없다는 의미 , 1 이상 : 카테고리의 pk번호
            if( bcno >= 1 ){ sql += " where bcno = "+bcno; } // 1. 전체보기 : select count(*) as 총게시물수 from board  // 2. 카테고리 보기 : select count(*) as 총게시물수 from board where bcno = 숫자
            // 검색이 존재 했을때, keyword가 존재하면
            if (searchKeyword.isEmpty()){}
            else { //비어있지 안흥면 검색이 있다라는 의미의 뜻 으로 활용
                // 카테고리가 있을때는 and 추가
                if(bcno>=1){sql+="and ";}
                //카테고리가 없을때 {전체보기}는 where 추가
                else {sql += " where ";}
                //검색 sql
                sql += searchKey+" like '%"+searchKeyword+"%' ";

            }
            System.out.println( " sql : " + sql );
            // 1. 전체보기 : select count(*) as 총게시물수 from board
            // 2. 카테고리 보기 : select count(*) as 총게시물수 from board where bcno = 숫자

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                return rs.getInt( 1 ); // "총게시물수"
            }
        }catch (Exception e ){ System.out.println("e = " + e); }
        return 0;
    }

    //글 전체 호출함수
    public ArrayList<BoardDto> ball(int starRow, int pageboardSize, int bcno, String searchKey, String searchKeyword){
        ArrayList<BoardDto> list = new ArrayList<>();
        try {String sql = "select * " +              // 1. 조회
                          " from board inner join member " +  // 2. 조인 테이블
                          " on board.no = member.no ";        // 3. 조인 조건
                          // 4. 일반 조건
                          // - 전체보기 이면 where절 생략 , bcno = 0 이면
                          // - 카테고리별 보기 이면 where 절 추가 , bcno >= 1 이상
        if( bcno >= 1 ){ sql += " where bcno = " + bcno ; }

        //4.일반 조건2
            if (searchKeyword.isEmpty()){}
            else {
                if(bcno >=1){sql += " and ";}
                else {sql += " where ";}
                sql += searchKey + " like '%"+searchKeyword+"%' ";
            }
            // 5. 정렬 조건 , 내림차순
            sql += " order by board.bno desc ";
            // 6. 레코드 제한 , 페이징처리
            sql += " limit ? , ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, starRow);
            ps.setInt(2, pageboardSize);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                list.add(BoardDto.builder().bno(rs.getLong("bno"))
                        .btitle(rs.getString("btitle")).id(rs.getString("id"))
                        .bdate(rs.getString("bdate")).bview(rs.getLong("bview")).build());

            }
        } catch (Exception e) {System.out.println(e);}
        return list;
    }

    //글 상세 호출 함수
    public BoardDto info(int bno){
        System.out.println("BoardDao.info");
        try{
            String sql = "select * from board join member join bcategory on board.bcno = bcategory.bcno where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return BoardDto.builder().bcname(rs.getString("bcname")).id(rs.getString("id")).bview(rs.getLong("bview")).
                        bdate(rs.getString("bdate")).btitle(rs.getString("btitle"))
                        .bcontent(rs.getString("bcontent")).bno(rs.getLong("bno")).bfile(rs.getString("bfile")).build();
            }
        } catch (Exception e){System.out.println(e);} return null;
    } //함수 종료

    //5. 조회수 증가 처리
    public boolean bviewIncrease(int bno){
        try{
            String sql = "update board set bview = bview + 1 where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            int count = ps.executeUpdate();
            if(count == 1) return true;
        } catch (Exception e){System.out.println(e);} return false;
    }


    //글 수정 함수
    public boolean bupdate(Map<String, String>map) {
        System.out.println("map = " + map);
        try{
            String sql = "update board set btitle = ?, bcontent = ?, bcno = ? where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,map.get("btitle"));
            ps.setString(2,map.get("bcontent"));
            ps.setInt(3,Integer.parseInt(map.get("bcno")));
            ps.setInt(4,Integer.parseInt(map.get("bno")));

            int count = ps.executeUpdate();
            if(count == 1){return true;}
        } catch (Exception e){System.out.println(e);} return false;
    }

    //글 삭제 함수
    public boolean bDelete(int bno){
        try{
            String sql = "delete from board where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            int count = ps.executeUpdate();
            if(count == 1){return true;}

        } catch (Exception e){System.out.println(e);} return false;
    }

} //DAO end
