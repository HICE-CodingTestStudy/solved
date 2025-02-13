package jeongHee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Hotel {
    //https://www.acmicpc.net/problem/1106
    //호텔
    static int C, N;
    static List<Info> list = new ArrayList<>();

    static class Info implements Comparable<Info>{
        int cost, result;

        public Info(int cost, int result) {
            this.cost = cost;
            this.result = result;
        }

        @Override
        public int compareTo(Info o) {
            return cost - o.cost;
        }
    }

    static int solution() {
        int[] cost = new int[C + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(0, 0));
        while (!pq.isEmpty()) {
            Info now = pq.poll();
            if(now.cost>cost[now.result]) continue;
            for (Info info : list) {
                int nextC = now.cost + info.cost;
                int nextR = Math.min(C, now.result + info.result);
                if(cost[nextR]<=nextC) continue;
                cost[nextR] = nextC;
                pq.add(new Info(nextC, nextR));
            }
        }
        return cost[C];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            list.add(new Info(c, r));
        }
        System.out.println(solution());
    }
}
