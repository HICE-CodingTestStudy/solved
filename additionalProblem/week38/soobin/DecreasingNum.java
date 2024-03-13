import java.io.*;

public class DecreasingNum {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, index;
    private static boolean isAnswerFound;
    private static long answer = -1;

    public static void main(String[] args) {
        parseInput();
        if (N < 1024) getNthDecreasingNum();
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
        } catch (IOException ignored) {}
    }

    public static void getNthDecreasingNum() {
        for (int length = 1; length <= 10; length++) {
            generateDecreasingNumber(0, length, 9);
            if (isAnswerFound) break;
        }
    }

    public static void generateDecreasingNumber(long number, int length, int limit) {
        if (length == 0) {
            if (++index == N) {
                isAnswerFound = true;
                answer = number;
            }
            return;
        }

        for (int n = 0; n <= limit; n++)
            generateDecreasingNumber(number * 10 + n, length - 1, n - 1);
    }

    private static void printAnswer(long answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
