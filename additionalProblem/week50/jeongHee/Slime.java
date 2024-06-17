package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Slime {
    //https://www.acmicpc.net/problem/14698
    //전생했더니 슬라임 연구자였던 건에 대하여 하드
    static final int MOD = 1000000007;
    static int N;
    static PriorityQueue<Long> pq;

    static long solution() {
        if (pq.size() <= 1) return 1;
        Long ans = 1L;
        while (pq.size() > 1) {
            Long cost = pq.poll() * pq.poll();
            ans *= cost % MOD;
            ans %= MOD;
            pq.add(cost);
        }
        return ans % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            pq = new PriorityQueue<>();
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                pq.add(Long.valueOf(st.nextToken()));
            }
            System.out.println(solution());
        }
    }
}
