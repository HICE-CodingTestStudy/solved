package week25.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class UnderPrime {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] numPrimes;
    private static int A, B;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            numPrimes = new int[B + 1];
        } catch (IOException e) {}
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int countPrimes(int n) {
        int count = 0;

        while (n % 2 == 0) {
            n /= 2;
            count++;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0) {
                n /= i;
                count++;
            }
        }

        if (n > 2) count++;

        return count;
    }

    private static void initialize() {
        for (int i = A; i <= B; i++)
            numPrimes[i] = countPrimes(i);
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0) return false;
        return true;
    }

    private static int countUnderPrimes() {
        int count = 0;
        for (int i = A; i <= B; i++) {
            if (numPrimes[i] == 1) continue;
            if (isPrime(numPrimes[i])) count++;
        }

        return count;
    }

    public static void main(String[] args) {
        parseInput();
        initialize();
        int answer = countUnderPrimes();
        printAnswer(answer);
    }
}
