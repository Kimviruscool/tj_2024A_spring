package example.day13;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class step1 {
    public static void main(String[] args) {

        //1. 스택 //(후입선출)
        Stack<Integer> coinBox = new Stack<>(); //객체생성

        //2. 스택에 push
        coinBox.push(100);
        coinBox.push(50);
        coinBox.push(500);
        coinBox.push(100);
        System.out.println("coinBox = " + coinBox);

        //4. 스택 Peek
        int Topdata = coinBox.peek();
        System.out.println("Topdata = " + Topdata); //가장 맨위에 데이터 출력

        //3. 스택에 pop //후입선출
        coinBox.pop();
        System.out.println("coinBox = " + coinBox); //가장 최근 입력값 꺼내기(삭제)
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);//최종적으로 값을 모두 제거하여 값이 없음

        
        //큐 생성자 생성
        Queue<Integer> pointBox = new LinkedList<>();

        //5. 큐 enqueue(추가)
        pointBox.offer(100);
        pointBox.offer(50);
        pointBox.offer(500);
        pointBox.offer(10);
        System.out.println("pointBox = " + pointBox);

        //6. 큐 Peek(확인)
        int frontdata  = pointBox.peek();
        System.out.println("Frontdata = " + frontdata);

        //7. 큐 dequeue(제거) //선입선출
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);

        
    }
}
