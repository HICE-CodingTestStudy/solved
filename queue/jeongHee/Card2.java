package queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//카드 2
//https://www.acmicpc.net/problem/2164
public class Card2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }
        while (queue.size()>1){
            queue.poll();
            queue.offer(queue.poll());
        }
        System.out.println(queue.poll());
    }
}
