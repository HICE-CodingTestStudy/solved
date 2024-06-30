package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Coupang {
    //https://www.acmicpc.net/problem/8980
    //택배
    static int N, C;
    static List<Info> infos = new ArrayList<>();

    static class Info implements Comparable<Info> {
        int from, to;
        int cost;

        @Override
        public int compareTo(Info o) {
            if (to != o.to) return to - o.to;
            return from - o.from;
        }

        public Info(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int solution() {
        Collections.sort(infos);
        int[] max = new int[N];
        Arrays.fill(max, C);
        int ans = 0;
        for (Info info : infos) {
            int maxCost = max[info.to];
            for (int i = info.from; i < info.to; i++) {
                maxCost = Math.min(maxCost, max[i]);
            }
            for (int i = info.from; i < info.to; i++) {
                max[i] -= Math.min(maxCost, info.cost);
            }
            ans += Math.min(maxCost, info.cost);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            infos.add(new Info(a, b, c));
        }
        System.out.println(solution());
    }
}
