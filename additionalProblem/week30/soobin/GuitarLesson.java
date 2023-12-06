package week30.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class GuitarLesson {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] lessons;
    private static int N, M, min, max;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            lessons = new int[N];

            min = Integer.MIN_VALUE; max = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                lessons[i] = Integer.parseInt(st.nextToken());
                min = Math.max(lessons[i], min);
                max += lessons[i];
            }
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int divide(int target) {
        int sum = 0, count = 0;
        for (int lesson : lessons) {
            if (sum + lesson > target) {
                count++;
                sum = 0;
            }
            sum += lesson;
        }
        if (sum != 0) count++;

        return count;
    }

    private static int solution() {
        int left = min, right = max;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (divide(mid) > M) left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = solution();
        printOutput(answer);
    }
}
