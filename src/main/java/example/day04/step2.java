package example.day04;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class step2 {
    public static void main(String[] args) {

        //문자열 에서 자주 사용되는 함수
        //[1] ssn.charAt(인덱스) : 해당 인덱스번호의 문자 1개 반환
        String ssn = "012345-1789198";
        char gender = ssn.charAt(7);
        switch (gender){
            case '1':
            case '3':
                System.out.println("남자");
                break;
            case '2':
            case '4':
                System.out.println("여자");
                break;
        }
        //활용 : Scanner 에서 문자 입력 메소드가 없다.
        Scanner scan = new Scanner(System.in);
        char _char = scan.next().charAt(0); //문자 1개 입력받기
        System.out.println(_char);

        //[2] 문자열.length : 문자열의 길이 반환하는 함수
        System.out.println(ssn.length());
        // 활용 : for문과 활용하면 문자1개 씩 순회
        for (int i = 0; i < ssn.length() ; i++){
            System.out.println(ssn.charAt(i));
        }

        //[3]문자열.replace(기존문자열 , 새로운문자열); :
        //      -기존문자열이 존재하면 새로운문자열로 (치환/교체/변경) 반환한다.
        String oldStr = "자바의 문자열은 불변입니다. 자바 문자열은 String 입니다.";
        String newStr = oldStr.replace("자바", "java");
        System.out.println(oldStr);
        System.out.println(newStr);
        //활용 : 서로 다른 언어들간의 문법 치환/교체 , HTML 줄바꿈<br/> 자바 \n
        String htmlStr = "안녕하세요<br/>유재석입니다.";
        System.out.println(htmlStr);
        String javaStr = htmlStr.replace("<br/>", "\n");
        System.out.println(javaStr);
        
        //[4] 문자열.subString(시작인덱스, [끝인덱스]) : 인덱스 기준으로 문자열 잘라낸 문자열 반환
            //sn = 012345-1234567
        String firstNum = ssn.substring(0,6); //0번 인덱스부터 6번 인덱스 전까지
        System.out.println(firstNum);

        String endNum = ssn.substring(7); //7번 인덱스부터 끝까지(인덱스를 생략 가능)
        System.out.println(endNum);

        //[5] 문자열.split("구문문자") : 문자열내 구분문자 기준으로 분해 해서 새로운 문자열 배열 반환
        String[] strArray = ssn.split("-"); //"-" 기준으로 문자열 분해
        //Arrays.toString(배열변수명) : 해당 배열의 요소들의 값들을 반환
        System.out.println(Arrays.toString(strArray));
        System.out.println(strArray[0]); //012345
        System.out.println(strArray[1]); //1789198

        //활용 : CSV형식 문자열 다루기
        String csvStr = "유재석,80,90,100\n강호동,70,50,90\n신동엽,30,60,40";
        //-행 구분자인 \n 기준으로 분해 해서 배열로 받기
        String[] rowStr = csvStr.split("\n");
        System.out.println(Arrays.toString(rowStr));

        //-열구분자인 기준으로 분해해서 배열로 받기
        for (int i = 0; i < rowStr.length; i++){
            String[] colsStr = rowStr[i].split(",");
            System.out.println(Arrays.toString(colsStr));
            for (int j = 0 ; j < colsStr.length; j++){
                System.out.println(colsStr[j]);
            }
        }

        //[6] 문자열.indexOf() : 문자열내 찾을 문자가 존재하면 찾은 인덱스 반환 , 없으면 -1
        String subject = "자바 프로그래밍 언어";

        int findIndex = subject.indexOf("자바"); //찾은 문자열의 인덱스 번호 반환
        System.out.println(findIndex); //자바 0 //프로 3 //파이썬 -1 //없으면 -1

        //[7] 문자열.containts() : 문자열내 찾을문자가 존재하면 true 반환 없으면 false 반환
        boolean findsub = subject.contains("자바");
        System.out.println(findsub); //자바 true //프로 true //파이썬 false

        //[8] 문자열.getByte : 문자열내 문자 하나씩 바이트로 변환된 바이트 배열로 반환
        byte[] bytes = subject.getBytes();
        System.out.println(Arrays.toString(bytes));

        //문자 char 와 바이트 byte 관계
            //영문/특수문자 : 문자1개당 1바이트
            //한글 : 문자1개당 2바이트 + 규칙1(UTF-8 인코딩해더규칙)바이트 => 총 3바이트
        byte _byte1 = 'a';
        System.out.println(_byte1); //97 //유니코드(아스키코드기반)
        _byte1++;
        System.out.println(_byte1); //98
        System.out.println((char)_byte1); //강제 형변환 B

        System.out.println("a".getBytes().length); //length : 1 //getBytes().length : 1 (영문)
        System.out.println("가".getBytes().length); //length : 1 //getBytes().length : 3 (한글)
        // byte : +-128
        // char : 문자1개표현 , 65536글자 표현(음수X) 부호:signed , 부호없음 : unsigned vs short : +-3만
        char _char1 = '가';
        System.out.println(_char1); //가
        System.out.println((int)_char1); //44032
        System.out.println('가'); //class(문자열) 아니라서 .(dot, 점) 연산자 사용불가능

        char _char2 = 'a';
        System.out.println((int)_char2); //강제형변환 //결과 : 97
            //[활용]
        System.out.println(new Random().nextInt()); //int타입의 허용범위내 난수 생성
        System.out.println(new Random().nextInt(26));//0~25 사이의 난수 생성
        System.out.println(new Random().nextInt(26)+97); //97~122 사이의 난수 생성
        System.out.println((char)(new Random().nextInt(26)+97)); //a~z 의 영소문자 난수 생성
        String newPwd = "";
        for (int i = 0 ; i < 10 ; i++){
            newPwd += (char)(new Random().nextInt(26)+97);

        }System.out.println(newPwd);

    } //main end
} //class end
