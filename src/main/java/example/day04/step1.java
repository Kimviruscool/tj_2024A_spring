package example.day04;

public class step1 {
    public static void main(String[] args) {

        //[1]문자열 선언하는 두가지 방법
        String str1 = new String("abc"); //[힙 영역] 302번지(객체) > 상수풀 참조
        String str2 = "test"; //상수풀(리터럴)[메소드 영역] 상수풀 참조
        String str3 = "tset"; //상수풀(리터럴)[메소드 영역] 상수풀 참조
        String str4 = new String("abc"); //[힙 영역] 402번지(객체) > 상수풀참조
        String str5 = "abc";

        System.out.println(str2 == str3); // true 참조변수의 참조주소가 같다.
        System.out.println(str2.equals(str3)); //true

        System.out.println(str1 == str4); // false 참조변수의 참조주소가 다르다
        System.out.println(str1.equals(str4)); // true

        //[2] 두 문자열 연결하는 방법
            //[2-1] 문자열1.concat(문자열2) : 문자열 연결 두 문자열을 연결한 새로운 문자열 반환 함수.
        String javaStr = new String("java");
        String androidStr = new String("android");
        System.out.println(System.identityHashCode(javaStr));

        javaStr = javaStr.concat(androidStr);
        System.out.println(javaStr);
        System.out.println(System.identityHashCode(javaStr));

        //[2-2] 문자열1 += 문자열2       :  변수 = 변수 + 값
        String html1 = "<div>";
        String html2 = "하하</div>";
        System.out.println(System.identityHashCode(html1));
        html1 += html2;
        System.out.println(System.identityHashCode(html1));

        //[2-3] StringBuilder : 기존 메모리 문자열을 사용하는 문자열 연결 클래스 , 메모리 효율성
        String javaStr2 = new String("java");
        String android2 = new String("android");
        System.out.println(System.identityHashCode(javaStr2));

        StringBuilder buffer = new StringBuilder(javaStr2);
        System.out.println(System.identityHashCode(buffer));

        buffer.append(android2);
        System.out.println(System.identityHashCode(buffer));

        javaStr2 = buffer.toString();

        System.out.println(javaStr2);
        System.out.println(System.identityHashCode(javaStr2));

    }
}
