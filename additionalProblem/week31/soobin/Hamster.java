package soobin;

import java.io.*;
import java.util.StringTokenizer;

public class Hamster {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] holes;
    private static int N, M;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            holes = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                holes[i] = Integer.parseInt(st.nextToken());
        } catch (IOException e) {}
    }

    private static void printAnswer(long answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static long solution() {
        int start = 0, end = 0;
        long max = 0L, current = 0L;

        while (end < N) {
            if (current + holes[end] <= M) {
                current += holes[end++];
                max = Math.max(max, current);
            }
            else {
                while (current + holes[end] > M) current -= holes[start++];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        parseInput();
        long answer = solution();
        printAnswer(answer);
    }
}
