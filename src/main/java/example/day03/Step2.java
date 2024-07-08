package example.day03;

public class Step2 {
    public static void main(String[] args) {

        //1. 2개의 문자열 객체 생성
        String str1 = new String("abc");    //str1 > 302번지 (멤버변수가 > 502번지 참조)
        String str2 = new String("abc");    //str2 > 402번지 (멤버변수가 > 502번지 참조)

        String str3 = "abc";                        //str3 > 502번지

        int i1 = 100;
        int i2 = 100;
        Integer i3 = 100;
        //Integer 는 왜 사용하는지?
        //래퍼(감싼)클래스 : int 형 기본타입은 메소드를 사용할 수 없으므로 int형 타입도 메소드를 사용하기 위해 만들어진 참조타입.
            //JAVA : Integer.parseInt() : 문자열 타입을 정수타입으로 *변환 , "10" vs 10 다르다.(문자열10과 정수10은 다르다)
            //vs JS : parseInt() : 문자열 타입을 정수타입으로 변환

        System.out.println(str1.hashCode());    //96354 ,   String 타입의 객체 같은 경우 문자열의 저장위치를 참조
        System.out.println(str2.hashCode());    //96354
        System.out.println(str3.hashCode());    //96354     //결론 : 2개의 객체의 멤버변수와 "abc"는 동일한 저장위치를 참조한다.
                                                            //- 자바 문자열 주소값은 동일하게 쓴다. (프로그래밍 언어 문자열은 불변)

        System.out.println(str1 == str2);       //false     302번지 == 402번지
        System.out.println(str1.equals(str2));  //true      302번지.equals(402번지) , true
        System.out.println(str1 == str3);       //false     302번지 == 502번지 , false
        System.out.println(str1.equals(str3));  //true      302번지.equals(502번지) , true

        System.out.println(i1 == i2);
      //System.out.println(i1.equals); //int는 참조타입이 아니므로 object로 부터 상속 받지 못했다.
        System.out.println(i3.equals(i1)); //Integer는 참조타입 이므로 object로 부터 상속 받았으므로 equals() 사용 가능하다.

        //Integer.parseInt()

        //
        //str1.clone(); // 기본적으로 clone() 메소드 사용이 불가능
        Object object = new Object();
        //object.clone(); // 기본적으로 clone() 메소드 사용이 불가능.

    }
}
