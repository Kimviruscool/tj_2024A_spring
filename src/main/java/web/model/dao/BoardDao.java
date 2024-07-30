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

    //글 전체 호출함수
    public ArrayList<BoardDto> ball(int starRow, int pageboardSize){
        ArrayList<BoardDto> list = new ArrayList<>();
        try {
            String sql = "select * from board inner join member on board.no = member.no order my board.bno desc limit ?,?; ";
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

} //DAO end
