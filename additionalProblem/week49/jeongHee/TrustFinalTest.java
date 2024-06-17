package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TrustFinalTest {
    //https://www.acmicpc.net/problem/23254
    //나는 기말고사형 인간이야
    static int N, M;
    static List<Info> infos;
    static PriorityQueue<Info> pq = new PriorityQueue<>();
    static int ans;


    static class Info implements Comparable<Info> {
        int min, up;

        @Override
        public int compareTo(Info o) {
            return o.up - up;
        }
    }

    static void solution() {
        pq.addAll(infos);
        while (!pq.isEmpty() && N != 0) {
            Info now = pq.poll();
            int time = Math.min((100 - now.min) / now.up, N);
            int score = time * now.up;
            ans += score;
            now.min += score;
            N -= time;
            now.up = Math.min(now.up, 100 - now.min);
            if (now.up != 0) pq.add(now);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken()) * 24;
        M = Integer.parseInt(st.nextToken());
        infos = new ArrayList<>();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            infos.add(new Info());
            infos.get(i).min = Integer.parseInt(st.nextToken());
            ans += infos.get(i).min;
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            infos.get(i).up = Integer.parseInt(st.nextToken());
        }
        solution();
        System.out.println(ans);
    }
}
