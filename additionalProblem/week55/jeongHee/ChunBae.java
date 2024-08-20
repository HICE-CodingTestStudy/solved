package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class ChunBae {
    //https://www.acmicpc.net/problem/30408
    //춘배가 선물하는 특별한 하트
    static long N, M;
    static Set<Long> now = new HashSet<>(), next = new HashSet<>();

    static boolean solution() {
        while (true) {
            if (now.isEmpty()) return false;
            for (Long i : now) {
                if (i == M) return true;
                if (i == 1)
                    continue;
                if (i % 2 == 0) {
                    next.add(i / 2);
                } else {
                    next.add((i - 1) / 2);
                    next.add((i + 1) / 2);
                }
            }
            now = new HashSet<>(next);
            next = new HashSet<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        now.add(N);
        System.out.println(solution() ? "YES" : "NO");
    }
}
