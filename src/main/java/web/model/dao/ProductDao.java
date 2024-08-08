package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.ProductDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component //dao 어노테이션 설정

public class ProductDao extends Dao {

    //파일 등록 함수
    public boolean pRegister(ProductDto productDto){
        //각테이블의 따른 DTO정보를 각 insert
        try{
            //제품 등록
            String sql = "insert into product(ptitle, pcontent, pprice) values(?,?,?) ";
            // --JDBC에서 insert 한 레코드의 자동번호가(auto_increament) 부여 된 PK번호를 반환하는 방법
                //1. conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)  생성된 키 반환받겠다.
                //2. ResultSet pkRs = ps.getGeneratedKeys();
                
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,productDto.getPtitle());
            ps.setString(2,productDto.getPcontent());
            ps.setInt(3,productDto.getPprice());
            int count = ps.executeUpdate(); //등록되었을때 1개 이상이거나 없을때 sql문 실행방지
            if (count == 1){ //등록된 레코드가1개 이면 (제품등록 성공)
                //제품 이미지 등록
                ResultSet pkRs = ps.getGeneratedKeys(); //생성된 pk번호들을 Resultset 반환
                if (pkRs.next()){ //Resultset.next() > 다음레코드 > pk 1개 존재하면
                    int pno = pkRs.getInt(1); //pk 번호추출
                    System.out.println("pno = " + pno); //확인
                }
                String sql2 = "insert into productimg(pimgname,pno) values(?,?)";

            }


        } catch (Exception e) {System.out.println(e);}
        return false;
    }

}
