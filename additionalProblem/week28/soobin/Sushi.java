package week28.soobin;

import java.io.*;
import java.util.*;

public class Sushi {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] sushi, eaten;
    private static int N, D, K, coupon;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            coupon = Integer.parseInt(st.nextToken());

            sushi = new int[N];
            for (int i = 0; i < N; i++)
                sushi[i] = Integer.parseInt(br.readLine());
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int init() {
        eaten[coupon] = 1;
        int count = 1;

        for (int i = 0; i < K; i++) {
            eaten[sushi[i]]++;
            if (eaten[sushi[i]] == 1) count++;
        }

        return count;
    }

    private static int solution() {
        eaten = new int[D + 1];

        int max = init(), start = 0, end = start + K, count = max;
        while (start < N) {
            int left = sushi[start], right = sushi[end % N];

            eaten[left]--;
            if (eaten[left] == 0) count--;
            eaten[right]++;
            if (eaten[right] == 1) count++;

            start++; end++;
            max = Math.max(max, count);
        }

        return max;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = solution();
        printOutput(answer);
    }
}
