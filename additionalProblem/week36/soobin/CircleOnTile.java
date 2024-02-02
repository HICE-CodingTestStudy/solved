import java.io.*;

public class CircleOnTile {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static long N;

    public static void main(String[] args) {
        parseInput();
        long answer = solution();
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            N = Long.parseLong(br.readLine()) / 2;
        } catch (IOException ignored) {}
    }

    private static long solution() {
        long count = 0;
        long x = 0, y = N - 1;

        while (x <= N && y >= 0) {
            long distance = calcDistance(x + 1, y);
            if (distance <= N * N) x++;
            if (distance >= N * N) y--;
            count++;
        }

        return count * 4;
    }

    private static long calcDistance(long x, long y) {
        return x * x + y * y;
    }

    private static void printAnswer(long answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
