package web.model.dao;

import example.day12.Member;
import org.springframework.stereotype.Component;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class MemberDao extends Dao {
    // 1. 회원가입
    public boolean mSignup( MemberDto memberDto ){
        System.out.println("MemberDao.mSignup");
        System.out.println("memberDto = " + memberDto);
        try{
            String sql = "insert into member( id , pw , name , email , phone ) values( ? , ? , ? , ? , ? )";
            PreparedStatement ps = super.conn.prepareStatement(sql);
            ps.setString( 1 , memberDto.getId() );
            ps.setString( 2 , memberDto.getPw() );
            ps.setString( 3 , memberDto.getName() );
            ps.setString( 4 , memberDto.getEmail() );
            ps.setString( 5 , memberDto.getPhone() );
            int count = ps.executeUpdate();
            if( count == 1 ) return true;
        }catch (Exception e ){  System.out.println("e = " + e);     }
        return false;
    }
    // 2. 로그인 : 로그인 성공한 회원번호 반환( 세션에 저장할려고 )
    public int mLogin( MemberDto memberDto ){
        System.out.println("MemberDao.mLogin");
        System.out.println("memberDto = " + memberDto);
        try{String sql = "select * from member where id = ? and pw =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1 , memberDto.getId() );
            ps.setString( 2 , memberDto.getPw() );
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){ return rs.getInt("no"); }
        }catch (Exception e ){ System.out.println(e);   }
        return 0; // 0 은 회원번호가 없다 뜻
    }
    // 5. 마이페이지 정보
    public MemberDto mMyInfo( int loginMno ){
        try{ String sql ="select * from member where no = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt( 1 , loginMno );
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                return MemberDto.builder()
                        .no( rs.getInt("no") )
                        .id( rs.getString("id"))
                        .phone( rs.getString("phone"))
                        .email( rs.getString("email"))
                        .name( rs.getString("name"))
                        .build();
            }
        }catch (Exception e ){  System.out.println(e);  } return null;
    }

    //6 아이디중복검사
    public boolean mIDCheck(String id){
        System.out.println("MemberDao.mIDCheck");
        System.out.println();
        try{
            String sql = "select id from member where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){return true;}
        }catch (Exception e){System.out.println(e);} return false;
    }

    //7 회원정보 삭제
    public boolean mleave(int loginNo, String pwConfirm){
        System.out.println("pwConfirm = " + pwConfirm);
        try{
            String sql = "delete from member where no = ? and pw = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,loginNo);
            ps.setString(2,pwConfirm);
            int count = ps.executeUpdate();
            if (count == 1 ){return true;}
        } catch(Exception e){System.out.println(e);}
        return false;
    }

    //8 회원정보 수정
    public boolean mupdate(int loginMno,String updateName, String nowPw, String updatePw, String updatePhone){
        try{
            String sql ="update member set pw = ?, name = ?, phone = ? where no = ? and pw = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,updatePw);
            ps.setString(2,updateName);
            ps.setString(3,updatePhone);
            ps.setInt(4,loginMno);
            ps.setString(5,nowPw);
            int count = ps.executeUpdate();
            if(count == 1){return true;}
            }catch (Exception e){  System.out.println(e);  } return false;
        }

} //class end







