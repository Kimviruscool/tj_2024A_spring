package example.day03;

class Book{ //class start
// 직접 extends Object 하지 않아도 자동으로 Object 클래스로부터 상속 받는다.
    //멤버변수
    int bookNumber;
    String bookTitle;

    //생성자
    public Book(int bookNumber, String bookTitle) { //생성자 start
        this.bookNumber = bookNumber;
        this.bookTitle = bookTitle;
    } //생성자 end

    //메소드
    public String toString(){
        return "Object Info : " + bookTitle+","+bookNumber;
    }
} //class end

public class Step1 {
    public static void main(String[] args) {
        //1. 객체 생성
        Book book1 = new Book(200, "개미"); //스택메모리에 : book1 =(참조) 힙메모리 : ex > 302번지 객체 생성
        
        //2. Book 클래스의 메소드가 아닌 Object 클래스의 메소드를 호출
        //참조 변수를 출력하면 toString() 가 자동으로 호출 된다. (주소값) ▼
        System.out.println(book1); // example.day03.Book@5ca881b5
        System.out.println(book1.toString()); //example.day03.Book@5ca881b5

        //3. 객체2 생성
        Book book2 = new Book(300, "TIGER"); //스택메모리에 : book2 =(참조) 힙메모리 : ex > 402번지 객체 생성

        //4. 객체3 생성
        Book book3 = new Book(200,"ANT"); //객체1 멤버변수 동일하게 생성 //스택메모리에 : book3 =(참조) 힙메모리 : ex > 502번지 객체 생성

        //5. 객체4 생성이 아닌 객체1 참조값 대입
        Book book4 = book1; //스택메모리에 : book4 =(참조) 힙메모리 : ex > 302번지 참조

        //▼ 연산자 비교
        System.out.println(book1 == book2); //false 다르다.                                 [302번지 == 402번지]
        System.out.println(book1 == book3); //false 다르다.                                 [302번지 == 502번지]
        System.out.println(book1 == book4); //true 같다. : 생성이 아닌 대입이라 같음 표시      [302번지 == 302번지]

        //▼ equals(주소값) 비교
        System.out.println(book1.equals(book2)); //false 다르다.                            [302번지.equals(주소값=402번지)]
        System.out.println(book1.equals(book3)); //false 다르다.                            [302번지.equals(주소값=502번지)]
        System.out.println(book1.equals(book4)); //true 같다. : 생성이 아닌 대입이라 같음 표시 [302번지.equals(주소값=302번지)]

    }

}
