package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WoodJumping {
    //https://www.acmicpc.net/problem/14712
    //넴모넴모
    static int[] woods;
    static PriorityQueue<Integer> pq;
    static int N;

    static int solution() {
        Deque<Integer> deque = new LinkedList<>();
        int ans = Integer.MIN_VALUE;
        deque.add(pq.poll());
        while (!pq.isEmpty()) {
            int nextLeft = pq.poll();
            ans = Math.max(ans,Math.abs(nextLeft-deque.peekFirst()));
            deque.addFirst(nextLeft);
            if(pq.isEmpty()) break;
            int nextRight = pq.poll();
            ans = Math.max(ans,Math.abs(nextRight-deque.peekLast()));
            deque.addLast(nextRight);
        }
        ans = Math.max(ans,Math.abs(deque.peekFirst()-deque.peekLast()));
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            woods = new int[N];
            pq = new PriorityQueue<>();
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                pq.add(Integer.parseInt(st.nextToken()));
            }
            System.out.println(solution());
        }
    }
}
