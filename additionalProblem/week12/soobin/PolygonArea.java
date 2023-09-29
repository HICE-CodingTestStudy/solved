package week12.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class PolygonArea {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        long[][] dots = new long[N + 1][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            dots[i][0] = x;
            dots[i][1] = y;
        }
        dots[N][0] = dots[0][0];
        dots[N][1] = dots[0][1];

        long sum_a = 0, sum_b = 0;
        for (int i = 0; i < N; i++) {
            sum_a += dots[i][0] * dots[i + 1][1];
            sum_b += dots[i + 1][0] * dots[i][1];
        }
        double area = Math.abs(sum_a - sum_b) / 2.0;

        bw.write(String.format("%.1f", area));
        bw.flush();
    }
}
