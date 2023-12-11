package week19.soobin;

import java.io.*;

public class PlayingWith1 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int parseInput() {
        try {
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {}

        return 0;
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int getSmallestNumLength(int N) {
        if (N % 5 == 0 || N % 2 == 0) return -1;

        int num = 1, count = 1;
        for (; num % N != 0; count++) num = (num * 10 + 1) % N;

        return count;
    }

    public static void main(String[] args) {
        int N = parseInput();
        int answer = getSmallestNumLength(N);
        printOutput(answer);
    }
}
