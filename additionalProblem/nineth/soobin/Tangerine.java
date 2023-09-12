package nineth.soobin;

import java.util.*;

public class Tangerine {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> tangerineSizes = new HashMap<>();
        for (int t : tangerine)
            tangerineSizes.put(t, tangerineSizes.getOrDefault(t, 0) + 1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int key : tangerineSizes.keySet())
            pq.add(new int[] {key, tangerineSizes.get(key)});

        int answer = 0;
        while (!pq.isEmpty() && k > 0) {
            int[] t = pq.poll();
            k -= t[1];
            answer++;
        }

        return answer;
    }
}
