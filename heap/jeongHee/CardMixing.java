package heap;

import java.math.BigInteger;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CardMixing {
    //https://www.acmicpc.net/problem/15903
    //카드 합체 놀이

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        //Big integer 필요
        PriorityQueue<BigInteger> cards = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            cards.add(sc.nextBigInteger());
        }
        for (int i = 0; i < m; i++) {
            BigInteger k = cards.poll().add(cards.poll());
            cards.add(k);
            cards.add(k);
        }
        BigInteger ans = BigInteger.ZERO;
        while (!cards.isEmpty()){
            ans=ans.add(cards.poll());
        }
        System.out.println(ans);
    }
}
