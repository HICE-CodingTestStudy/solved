package week3.soobin;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DefenceGame {
    private boolean condition(int round, long n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i <= round; i++) {
            pq.add(enemy[i]);
            n -= enemy[i];
        }

        for (int i = 0; i < k; i++)
            n += pq.poll();

        return n >= 0;
    }

    public int solution(int n, int k, int[] enemy) {
        if (k >= enemy.length) return enemy.length;

        int l = 0, r = enemy.length - 1;

        while (l < r) {
            int m = (l + r + 1) / 2;

            if (condition(m, n, k, enemy)) l = m;
            else r = m - 1;
        }

        return l + 1;
    }

    public static void main(String[] args) {
        DefenceGame d = new DefenceGame();
        System.out.println(d.solution(7, 3, new int[]{4,2,4,5,3,3,1}));
        System.out.println(d.solution(2, 4, new int[]{3,3,3,3}));
    }
}
