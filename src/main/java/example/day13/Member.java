package example.day13;

public class Member implements Comparable<Member> {
    String name;
    int age;

    public Member(int age, String name) {
        this.age = age;
        this.name = name;
    }

    // *Comparable 인터페이스의 추상메소드 정의
    @Override
    public int compareTo(Member o){
        //1. name 기준으로 정렬(가나다순) , String 클래스내 compareTo 활용
//        return this.name.compareTo(o.name);
        //2. age 기준으로 정렬 (오름차순) , int 기본타입 으로(compareTo가 없어) 직접 정렬기준 비교하기
            // 오름차순 : -1 : 매개변수보다 작으면  , 0 : 매개변수와 같으면  , 1 : 매개변수보다 크면
            // 내림차순 : 1 : 매개변수보다 작으면  , 0 : 매개변수와 같으면  , -1 : 매개변수보다 크면
        if(this.age < o.age) {return -1;}
        else if(this.age == o.age){return 0;}
        else return 1;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
//
