package queue;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CardSort {
    // https://www.acmicpc.net/problem/1715
    // 카드 정렬하기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        PriorityQueue<Integer> card = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            card.add(sc.nextInt());
        }
        int ans = 0;
        while (!card.isEmpty()){
            int newCard = card.poll();
            if(card.isEmpty()){
                System.out.println(ans);
                return;
            }
            newCard +=card.poll();
            ans+=newCard;
            card.add(newCard);
        }
    }
}
