import java.io.*;

public class Pill {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final long[] catalanNums = new long[31];

    public static void main(String[] args) throws IOException {
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            bw.write(catalan(N) + "\n");
        }
        bw.flush();
    }

    private static long catalan(int n) {
        if (n == 1) return 1;

        if (catalanNums[n] > 0) return catalanNums[n];

        return catalanNums[n] = catalan(n - 1) * (4 * n - 2) / (n + 1);
    }
}
