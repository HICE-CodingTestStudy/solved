package week28.soobin;

import java.util.*;

public class NightShift {
    public long solution(int n, int[] works) {
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) pq.add(work);

        while (!pq.isEmpty() && n-- > 0) {
            int work = pq.poll();
            if (work >= 1) pq.add(work - 1);
        }

        long answer = 0;
        while (!pq.isEmpty()) {
            int work = pq.poll();
            answer += Math.pow(work, 2);
        }

        return answer;
    }
}
