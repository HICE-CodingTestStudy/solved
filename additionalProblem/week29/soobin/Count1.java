package week29.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class Count1 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static long[] sum;
    private static long A, B;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());
            sum = new long[55];
        } catch (IOException e) {}
    }

    private static void printOutput(long answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void init() {
        sum[0] = 1L;
        for (int i = 1; i < 55; i++)
            sum[i] = (sum[i - 1] * 2) + (1L << i);
    }

    private static long count(long n) {
        long count = n & 1;

        for (int i = 54; i > 0; i--) {
            long bit = 1L << i;
            if ((n & bit) != 0L) {
                count += sum[i - 1] + (n - bit + 1);
                n -= bit;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        parseInput();
        init();
        long answer = count(B) - count(A - 1);
        printOutput(answer);
    }
}
