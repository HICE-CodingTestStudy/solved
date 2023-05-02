package heap;

import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KthNumber {
    //https://www.acmicpc.net/problem/11004
    //K번째 수
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        int N = sc.nextInt();
        int K = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        for (int i = 0; i < K-1; i++) {
            numbers.poll();
        }
        System.out.println(numbers.poll());
    }

}
