package heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Christmas {
    //https://www.acmicpc.net/problem/14235
    //크리스마스 선물

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> numbers = new PriorityQueue<>(Collections.reverseOrder());
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int thisNumber = sc.nextInt();
            if(thisNumber==0){
                if(numbers.isEmpty())
                    System.out.println(-1);
                else System.out.println(numbers.poll());
                continue;
            }
            for (int j = 0; j < thisNumber; j++) {
                numbers.add(sc.nextInt());
            }
        }
    }
}
