package example.day13;

import java.util.TreeSet;

public class step3 {
    public static void main(String[] args) {

        //1.
        TreeSet<Member> members = new TreeSet<>();
        // 오류
        //class example.day13.Member cannot be cast to class java.lang.Comparable (example.day13.Member is in unnamed module of loader 'app'; java.lang.Comparable is in module java.base of loader 'bootstrap')
        //2.
        members.add(new Member(29, "홍길동"));
        members.add(new Member(40, "유재석"));
        members.add(new Member(35, "강호동"));
        members.add(new Member(24, "신동엽"));
        System.out.println("members = " + members);
    }
}
