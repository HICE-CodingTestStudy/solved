package week23.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class IHateChange {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static long[][] fractions; // 0: 분자, 1: 분모

    public static void parseInput() {
        try {
            int N = Integer.parseInt(br.readLine());
            fractions = new long[N][2];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long n = Integer.parseInt(st.nextToken());
                long m = Integer.parseInt(st.nextToken());

                // 기약분수로 만들기
                long gcd = gcd(n, m);
                fractions[i] = new long[] {n / gcd, m / gcd};
            }
        } catch (IOException e) {}
    }

    private static void printOutput(long[] answer) {
        try {
            bw.write(String.format("%d %d", answer[0], answer[1]));
            bw.flush();
        } catch (IOException e) {}
    }

    private static long gcd(long n, long m) {
        return n % m == 0 ? m : gcd(m, n % m);
    }

    private static long[] getNewCoinUnit() {
        long[] coinUnit = fractions[0];
        for (int i = 1; i < fractions.length; i++) {
            long[] a = fractions[i];

            // 분모들의 최소 공배수
            long gcd = gcd(a[1], coinUnit[1]);
            coinUnit[1] = (a[1] * coinUnit[1]) / gcd;

            // 분자들의 최대 공약수
            coinUnit[0] = gcd(a[0], coinUnit[0]);
        }

        long gcd = gcd(coinUnit[0], coinUnit[1]);
        coinUnit[0] /= gcd;
        coinUnit[1] /= gcd;
        return coinUnit;
    }

    public static void main(String[] args) {
        parseInput();
        long[] answer = getNewCoinUnit();
        printOutput(answer);
    }
}
