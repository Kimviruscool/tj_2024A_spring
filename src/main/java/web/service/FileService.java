package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileService {
    //[0] 파일이 저장된 위치 경로 정의 필드
    String uploadPath = "C:\\Users\\tj-bu-703-021\\Desktop\\Spring\\src\\main\\resources\\static\\upload\\";

    //[1] 파일 업로드 : 매개변수로 파일의바이트가 저장된 MultipartFile 인터페이스
    //업로드 된 파일명 반환
    public String fileUpload(MultipartFile multipartFile){
        //1. 첨부 파일의 실제 파일 이름 추출
            //+ 클라이언트 들이 서로 다른 파일내용의 같은 파일명으로 업로드 했을때 식별이 불가능.
            //해경방안 : 1. UUID(고유성 보장하는 ID 규약) 2. 조합식별 설계(주로 업로드날짜/시간 와 파일명 조합)
        String uuid = UUID.randomUUID().toString(); // 난수의 UUID 생성 , 임의으이 UUID 규약에 따른 문자열 생성
        System.out.println("uuid = " + uuid);

        //1. 파일 실제 이름 추출
        String fileName = multipartFile.getOriginalFilename();

        //UUID + 파일이름 합치기 , UUID와 파일명을 구분하는 문자 조합 , 파일명의_(언더바)가 존재하면 안된다.
        //추후에 _(언더바) 기준으로 분리하면 ( [0]UUID [1]파일명 ) 으로 분리가능
        //"문자열".replaceAll("기존문자", "새로운문자") : 만약에 문자열내 기존문자가 존재하면 새로운문자로 지환해서 반환
        fileName = uuid +"_"+fileName.replaceAll("_","-"); //파일명 내에 (_) 가 존재하면 (-)로 변환

        System.out.println("fileName = " + fileName);
        //2. 첨부파일을 저장/복사/이동할 저장할 경로 만들기
        //3. 저장할 경로 와 파일명 합치기
        String filePath = uploadPath+fileName;
        //4. 해당 경로로 설정한 file 객체 , transferTo (file객체)
        File file = new File(filePath);
        //5. transfertO(file객체) : file 객체내 설정한 해당 경로 로 파일 복사/저장/이동
        //일반 예외 무조건 발생
        try {
            multipartFile.transferTo(file);
            return fileName;
        } catch (Exception e){System.out.println(e); }return null;

    }

    @Autowired HttpServletRequest request; //HTTP요청 객체 , HTTP로 요청 들어온 정보 와 기능이 포함된 객체
    @Autowired HttpServletResponse response; //HTTP 응답 객체, HTTP로 응답 할때 정보와 기능이 포함된 객체

    //[2] 파일 다운로드
    public void fileDownload(String filename){

        //1. 다운로드 할 경로 설정 uploadPath
            //1. 업로드 경로 와 다운로드 할 파일명 조합
        String downLoadPath = uploadPath+filename;
        //- File클래스는 file에 관련된 다양한 메소드 제공 ,
            //exists() : 해당 경로의 파일이 존재하면 true 없으면 false
            //-length() : 해당 경로의 파일이 존재하면 파일의 용량을 바이트 개수로 반환 (용량찾기)
            
        File file = new File(downLoadPath);
        if(!file.exists()){return;} //파일이 존재하지 않으면 빠져나감

        //2. 해당 다운로드 할 파일을 서버(자바)의 바이트형식으로 읽어 들이기
            //- 스트림 : 자바 외부 와 통신시 바이트가 다니는 통로 
            //- InputStream : 읽어드리는 통로 , OutputStream : 내보내는 통로
            //- Buffer 버퍼 : 스트림내 통로에서 이동하는 동안 일시적으로 데이터를 보관하는 메모리(스트림 에서도 사용)
        try {
            //===================================== 파일을 바이트 배열로 불러오기 =========================
            //2-1 파일 입력스트림 객채 생성
            BufferedInputStream fin = new BufferedInputStream(new FileInputStream(downLoadPath));
            //2-2 파일의 용량만큼 배열 선언
                //파일의 용량
            long fileSize = file.length();
                //파일의 용량만큼 배열의 길이 선언
            byte[] bytes = new byte[(int)fileSize];
                //.read(배열명) : 해당 파일을 읽어서 바이트들을 해당 배열에 하나씩 대입한다.
            fin.read(bytes); //경로에 해당 하는 파일을 바이트로 가져오기
            //- 버퍼닫기
            fin.close();
            System.out.println(" Arrays.toString(bytes) = " + Arrays.toString(bytes));
            //================================== 읽어온 바이트배여을 HTTP 바이트 형식으로 응답하기============
        //[3] HTTP 스트림으로 응답하기
            //3-1 BufferedOutputStream fout = new BufferedOutputStream(출력할 대상의 스트림객체);
            BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());

        //--- HTTP 응답의 헤더 속성 추가 (.setHeader(key, value))
            //content-disposition : 브라우저가 제공하는 다운로드 형식
            //attachment;filename="다운로드에 표시될 파일명"
                //URLEncoder.encode() : URL 경로상의 한글을 인코딩
                //filename.split("_")[1] : _ 기준으로 분해해서 UUID 를 제외한 실제 파일명만 추출

            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename.split("_")[1] , "UTF-8"));

            //3-2 바이트 배열 내보내기/출력/쓰기 .write
            fout.write(bytes);
            //- 버퍼닫기
            fout.close();
            
        } catch (Exception e) {System.out.println(e);}
        }
}
