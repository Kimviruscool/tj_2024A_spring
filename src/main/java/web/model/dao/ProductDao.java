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
            if (count == 1){ //등록된 레코드가1개 이면 (제품등록 성공) //if 1 start
                //제품 이미지 등록
                ResultSet pkRs = ps.getGeneratedKeys(); //생성된 pk번호들을 Resultset 반환
                if (pkRs.next()){ //Resultset.next() > 다음레코드 > pk 1개 존재하면 //if 2 start
                    int pno = pkRs.getInt(1); //pk 번호추출
                    System.out.println("pno = " + pno); //확인

                    productDto.getFileNames().forEach( fileName -> { //forEach start
                        try { //다른 함수추가 이기때문에 try , catch 한번더 따로 사용
                        String sql2 = "insert into productimg(pimgname,pno) values(?,?)";
                        PreparedStatement ps1 = conn.prepareStatement(sql2);
                        ps1.setString(1,fileName);
                        ps1.setInt(2,pno);
                        ps1.executeUpdate();
                        } catch (Exception e) {System.out.println(e); }
                    }); //forEach end
                } //if 2 end
            } //if 1 end
        } catch (Exception e) {System.out.println(e);} return false;
    } //method end

    //제품 출력 함수 (1개 : DTO) (여러개 : list<DTO> )
    public List<ProductDto> getProductFindAll(){
        List<ProductDto> list = new ArrayList<>();
        try{
            // 1. 제품 조회
            String sql = "select * from product"; //전체조회
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ //반복문 돌려서 목록 전체조회 //while 1 start
                // - 제품 객체 생성
                // pno,pdate,pview DTO 에 추가
                ProductDto productDto = ProductDto.builder()
                        .pno(rs.getInt("pno"))
                        .ptitle(rs.getString("ptitle"))
                        .pcontent(rs.getString("pcontent"))
                        .pprice(rs.getInt("pprice"))
                        .pdate(rs.getString("pdate"))
                        .pview(rs.getInt("pview"))
                        .build();
                // 2. 제품 모든 이미지 조회 : fileNames
                List<String> fileNames = new ArrayList<>();
                    //해당 제품의 모든 이미지 조회
                String sql2 = "select * from productimg where pno = ?";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1,rs.getInt("pno"));
                ResultSet rs2 = ps2.executeQuery();
                while(rs2.next()){ //while 2 start
                    fileNames.add(rs2.getString("pimgname"));
                } //while 2 end
                //조회한 모든 이미지를 DTO에 담기
                productDto.setFileNames(fileNames);
                // - 제품 객체 리스트에 담기
                list.add(productDto);
            } //while 1 end
        } catch (Exception e) {System.out.println(e);} return list; //제품 리스트 반환
    } //method end


} //class end
