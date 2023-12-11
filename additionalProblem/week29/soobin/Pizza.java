package week29.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class Pizza {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] oven, pizzas;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            oven = new int[D];
            pizzas = new int[N];

            st = new StringTokenizer(br.readLine());
            oven[0] = Integer.parseInt(st.nextToken());
            for (int i = 1; i < D; i++) {
                int size = Integer.parseInt(st.nextToken());
                oven[i] = Math.min(size, oven[i - 1]);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                pizzas[i] = Integer.parseInt(st.nextToken());
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int upperBound(int target, int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (oven[mid] >= target) left = mid + 1;
            else right = mid;
        }

        return left - 1;
    }

    private static int solution() {
        int top = oven.length;

        for (int pizza : pizzas) {
            top = upperBound(pizza, 0, top);
            if (top < 0) return 0;
        }

        return top + 1;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = solution();
        printOutput(answer);
    }
}
