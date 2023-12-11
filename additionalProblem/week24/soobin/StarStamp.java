package week24.soobin;

import java.io.*;
import java.util.Arrays;

public class StarStamp {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private static char[][] board;
    private static int N;

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            N = 1 + 4 * (N - 1);
            board = new char[N + 2][N];
            Arrays.fill(board[0], '*');
        } catch (IOException e) {}
    }

    private static void print() {
        try {
            for (int i = 0; i < N + 2; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < N; j++) {
                    sb.append(board[i][j] == '*' ? board[i][j] : ' ');
                }

                // 마지막 * 이후 공백 제거
                int lastStart = N - 1;
                for (int j = N - 1; j >= 0; j--) {
                    if (board[i][j] == '*') {
                        lastStart = j;
                        break;
                    }
                }
                sb.delete(lastStart + 1, sb.length());
                bw.write(sb + "\n");
            }
            bw.flush();
        } catch (IOException e) {}
    }

    private static void draw() {
        int curR = 1, curC = 0;
        int dir = 1, limit = N, temp = 0, count = 0;

        while (true) {
            while (temp < limit) {
                board[curR][curC] = '*';
                curR += moves[dir][0];
                curC += moves[dir][1];
                temp++;
            }
            // 가로 방향이었으면 다음 인덱스로 넘어간 걸 그대로 쓰면 안되니까 한 번 뒤로가기
            if (dir == 0 || dir == 2) {
                curR -= moves[dir][0];
                curC -= moves[dir][1];
            }

            if (limit == 2 && dir == 1) break;

            temp = 0;
            count++;
            dir =  dir == 3 ? 0 : dir + 1;
            // 이전에 가로 방향이었을 때 뒤로 가기 했으니까 다시 제대로 된 다음 위치로 이동
            if (dir == 1 || dir == 3) {
                curR += moves[dir][0];
                curC += moves[dir][1];
            }

            // 두 번씩 찍으면 다음 길이는 이전 길이 -2 (마지막만 -1)
            if (count == 2) {
                limit -= limit == 3 ? 1 : 2;
                count = 0;
            }
        }
    }

    public static void main(String[] args) {
        parseInput();
        if (N == 1) {
            System.out.println("*");
            return;
        }
        draw();
        print();
    }
}
