package web.model.dao;

import org.springframework.stereotype.Component;

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

}
