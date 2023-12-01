package week29.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class Stair {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] histogram, memo;

    private static void parseInput() {
        try {
            int N = Integer.parseInt(br.readLine());
            histogram = new int[N];
            memo = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                histogram[i] = Integer.parseInt(st.nextToken());
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int solution() {
        memo[0] = 1;
        for (int i = 1; i < histogram.length; i++)
            memo[i] = histogram[i] >= memo[i - 1] + 1 ? memo[i - 1] + 1 : histogram[i];

        int answer = memo[0];
        for (int height : memo) answer = Math.max(answer, height);

        return answer;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = solution();
        printOutput(answer);
    }
}
