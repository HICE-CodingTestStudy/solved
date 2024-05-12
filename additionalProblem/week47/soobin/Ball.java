import java.io.*;

public class Ball {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static char[] balls;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        balls = br.readLine().toCharArray();
        int answer = solution();
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int solution() {
        int toRight = Math.min(moveBalls('R', 0, 1), moveBalls('B', 0, 1));
        int toLeft = Math.min(moveBalls('R', N - 1, -1), moveBalls('B', N - 1, -1));
        return Math.min(toRight, toLeft);
    }

    private static int moveBalls(char ball, int start, int dir) {
        int total = 0, count = 0;

        while (start >= 0 && start < N) {
            if (balls[start] == ball) count++;
            else {
                total += count;
                count = 0;
            }
            start += dir;
        }

        return total;
    }
}
