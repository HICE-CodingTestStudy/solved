package additional2;

import java.util.Collections;
import java.util.PriorityQueue;

public class WorkingLate {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12927
    //야근 지수
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            pq.add(work);
        }
        while (n-- > 0) {
            if(pq.peek()==0) break;
            pq.add(pq.poll() - 1);
        }
        long ans = 0;
        while (!pq.isEmpty())
            ans += Math.pow(pq.poll(), 2);
        return ans;
    }
}
