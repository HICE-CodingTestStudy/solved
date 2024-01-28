package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Mosquito {
    //https://www.acmicpc.net/problem/20440
    //니가 싫어
    static int N;
    static PriorityQueue<Integer> in = new PriorityQueue<>();
    static PriorityQueue<Integer> out = new PriorityQueue<>();

    private static void solution() {
        int count = 0;
        int ans = 0;
        int ansStart = Integer.MAX_VALUE, ansEnd = 0;
        int now = in.peek();
        boolean isAnswerAppeared = false;
        while (true) {
            if (!out.isEmpty()) now = out.peek();
            if (!in.isEmpty()) now = Math.min(in.peek(), now);
            while (!in.isEmpty() && in.peek() <= now) {
                in.poll();
                count++;
            }
            while (!out.isEmpty() && out.peek() <= now) {
                out.poll();
                count--;
            }
            if (count > ans) {
                ansStart = now;
                ans = count;
                isAnswerAppeared = false;
            }
            if (count < ans && !isAnswerAppeared) {
                ansEnd = now;
                isAnswerAppeared = true;
            }
            if (out.isEmpty()) break;
        }
        System.out.println(ans);
        System.out.println(ansStart + " " + ansEnd);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            in.add(a);
            out.add(b);
        }
        solution();
    }
}
