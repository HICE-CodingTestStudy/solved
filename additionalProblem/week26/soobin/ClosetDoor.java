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

    private static void solution(int idx, int cost) {
        if (idx == K) {
            answer = Math.min(answer, cost);
            return;
        }

        if (opened[0] == usage[idx] || opened[1] == usage[idx]) {
            solution(idx + 1, cost);
            return;
        }

        int temp = opened[0];
        // 0 번째 벽장을 닫는 경우
        opened[0] = usage[idx];
        solution(idx + 1, cost + Math.abs(temp - usage[idx]));
        opened[0] = temp;

        // 1 번째 벽장을 닫는 경우
        temp = opened[1];
        opened[1] = usage[idx];
        solution(idx + 1, cost + Math.abs(temp - usage[idx]));
        opened[1] = temp;
    }

    public static void main(String[] args) {
        parseInput();
        solution(0, 0);
        printOutput();
    }
}
