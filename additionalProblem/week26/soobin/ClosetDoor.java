package week26.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class ClosetDoor {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] usage, opened = new int[2];
    private static int answer = Integer.MAX_VALUE, K;

    private static void parseInput() {
        try {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            opened[0] = Integer.parseInt(st.nextToken());
            opened[1] = Integer.parseInt(st.nextToken());

            K = Integer.parseInt(br.readLine());
            usage = new int[K];
            for (int i = 0; i < K; i++)
                usage[i] = Integer.parseInt(br.readLine());
        } catch (IOException e) {}
    }

    private static void printOutput() {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void solution(int opened1, int opened2, int idx, int cost) {
        if (idx == K) {
            answer = Math.min(answer, cost);
            return;
        }

        if (opened1 == usage[idx] || opened2 == usage[idx]) {
            solution(opened1, opened2, idx + 1, cost);
            return;
        }

        solution(usage[idx], opened2, idx + 1, cost + Math.abs(opened1 - usage[idx]));
        solution(opened1, usage[idx], idx + 1, cost + Math.abs(opened2 - usage[idx]));
    }

    public static void main(String[] args) {
        parseInput();
        solution(opened[0], opened[1], 0, 0);
        printOutput();
    }
}
