package first.soobin;

import java.io.*;

public class NQueen {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] position;

    private static int answer;
    private static int N;

    private static boolean isSettable(int row, int col) {
        for (int i = row - 1; i >= 0; i--) {
            int rowPosition = position[i];
            // 일직선
            if (rowPosition == col) return false;
            // 왼 대각선
            if (rowPosition != 0 && rowPosition - (row - i) == col) return false;
            // 오른 대각선
            if (rowPosition != N - 1 && rowPosition + (row - i) == col) return false;
        }
        position[row] = col;
        return true;
    }

    private static void queen(int row, int col) {
        if (row == N) {
            answer++;
            return;
        }

        if (!isSettable(row, col)) return;

        for (int i = 0; i < N; i++) {
            // 불가능한 자리 건너뛰기
            if (col == 0 && (i == 0 || i == 1)) continue;
            else if (col == N - 1 && (i == N - 1 || i == N - 2)) continue;
            else if (i == col - 1 || i == col || i == col + 1) continue;

            queen(row + 1, i);
            if (row + 1 == N) break;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        position = new int[N];

        if (N == 1) answer = 1;
        else if (N == 2 || N == 3) answer = 0;
        else {
            long start = System.currentTimeMillis();
            for (int i = 0; i < N; i++)
                queen(0, i);
            long end = System.currentTimeMillis();
            System.out.println((end - start) / 1000);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
