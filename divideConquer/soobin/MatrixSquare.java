package soobin;

import java.io.*;
import java.util.StringTokenizer;

public class MatrixSquare {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final long MOD = 1000L;

    private static long[][] A;

    private static long[][] matMul(long[][] a, long[][] b) {
        int n = a.length;
        long[][] result = new long[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++) {
                    result[i][j] += a[i][k] * b[k][j] % MOD;
                    result[i][j] %= MOD;
                }
        return result;
    }

    private static long[][] divide(long n) {
        if (n <= 1) return A;

        long[][] mat = divide(n / 2);
        if (n % 2 == 1)
            return matMul(matMul(mat, mat), A);
        else
            return matMul(mat, mat);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        A = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }

        A = divide(B);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                bw.write(String.format("%d ", A[i][j] % MOD));
            bw.newLine();
        }
        bw.flush();
    }
}
