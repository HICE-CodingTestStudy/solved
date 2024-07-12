package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class YeolRamSil {
    //https://www.acmicpc.net/problem/28018
    //시간이 겹칠까?
    static int N, Q;
    static Comparator<Info> orderByS = new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.s - o2.s;
        }
    };
    static Comparator<Info> orderByE = new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.e - o2.e;
        }
    };
    static PriorityQueue<Info> infos = new PriorityQueue<>(orderByS);
    static PriorityQueue<Info> questions = new PriorityQueue<>(orderByE);


    static class Info {
        int s, e;

        public Info(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    static void solution() {
        int[] ans = new int[Q];
        StringBuilder sAns = new StringBuilder();
        PriorityQueue<Info> out = new PriorityQueue<>(orderByE);
        for (int i = 0; i <= 1000000; i++) {
            while (!out.isEmpty() && out.peek().e < i)
                out.poll();
            while (!infos.isEmpty() && infos.peek().s <= i)
                out.add(infos.poll());
            if (questions.isEmpty()) break;
            while (!questions.isEmpty() && questions.peek().e == i) {
                ans[questions.poll().s] = out.size();
            }
        }
        for (int i = 0; i < Q; i++) {
            sAns.append(ans[i]).append("\n");
        }
        System.out.print(sAns);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            infos.add(new Info(s, e));
        }
        Q = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < Q; i++) {
            questions.add(new Info(i, Integer.parseInt(st.nextToken())));
        }
        solution();
    }
}
