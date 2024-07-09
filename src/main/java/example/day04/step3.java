package example.day04;

import java.util.Arrays;

public class step3 {
    public static void main(String[] args) {

        String money = "123123123";
        //문제 : money 변수의 존재하는 문자열 금액의 천단위 쉼표를 추가하시오
        String moneya = money.replace("3","3,");
        String moneyb = moneya.substring(0,11);
        System.out.println(moneyb);

    }
}
