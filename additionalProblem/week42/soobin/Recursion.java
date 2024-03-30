import java.io.*;

public class Recursion {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final long[][][] memo = new long[21][21][21];

    private static int a, b, c;

    public static void main(String[] args) throws IOException {
        while (true) {
            parseInput();
            if (isEnd()) break;
            long result = w(a, b, c);
            print(result);
        }
        bw.flush();
    }

    private static void parseInput() throws IOException {
        String[] input = br.readLine().split(" ");
        a = Integer.parseInt(input[0]);
        b = Integer.parseInt(input[1]);
        c = Integer.parseInt(input[2]);
    }

    private static boolean isEnd() {
        return a == -1 && b == -1 && c == -1;
    }

    private static long w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) return 1;
        if (a > 20 || b > 20 || c > 20) return w(20, 20, 20);

        if (memo[a][b][c] > 0) return memo[a][b][c];

        return memo[a][b][c] = a < b && b < c
                ? w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c)
                : w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
    }

    private static void print(long result) throws IOException {
        String format = String.format("w(%d, %d, %d) = ", a, b, c);
        bw.write(format + result + "\n");
    }
}
