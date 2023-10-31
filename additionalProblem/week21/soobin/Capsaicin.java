package week21.soobin;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Capsaicin {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MOD = 1000000007;

    private static List<Integer> scoville = new ArrayList<>();
    private static long[] pow;
    private static long answer;
    private static int N;

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            pow = new long[N + 1];
            pow[0] = 1;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                scoville.add(Integer.parseInt(st.nextToken()));
                pow[i + 1] = (pow[i] * 2) % MOD;
            }
            Collections.sort(scoville);
        } catch (IOException e) {}
    }

    private static void printOutput() {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            answer += scoville.get(i) * (pow[i] - pow[N - i - 1]);
            answer %= MOD;
        }
    }

    public static void main(String[] args) {
        parseInput();
        solution();
        printOutput();
    }
}
