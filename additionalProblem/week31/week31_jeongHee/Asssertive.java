package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Asssertive {
    //https://www.acmicpc.net/problem/25916
    //싫은데요
    static long N, M;
    static long ans = 0, sum = 0;
    static Deque<Long> deque = new LinkedList<>();

    static void solution(long next) {
        while (sum + next > M && !deque.isEmpty()) {
            sum -= deque.pollFirst();
        }
        if (sum + next <= M) {
            deque.addLast(next);
            sum += next;
            ans = Math.max(ans, sum);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            solution(Long.parseLong(st.nextToken()));
        }
        System.out.println(ans);
    }
}
