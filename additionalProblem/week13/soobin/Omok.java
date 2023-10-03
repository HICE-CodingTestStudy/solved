package week13.soobin;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Omok {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}};    // ↑, ↖, ←, ↙, ↗, →, ↘, ↓
    private static final char[][] board = new char[19][19];
    private static final Queue<int[]> blacks = new LinkedList<>(), whites = new LinkedList<>();

    private static int[] won = new int[2];

    private static boolean isValid(int i, int j) {
        return i >= 0 && i < 19 && j >= 0 && j < 19;
    }

    private static boolean isFirst(int i, int j, char type, int moveIdx) {
        int[] prev = moves[moveIdx], post = moves[7 - moveIdx];

        // 탐색하는 방향으로 다음 위치를 파악할 수 없으면 어차피 오목이 아닐 것이므로 패스
        if (!isValid(i + post[0], j + post[1])) return false;

        return !isValid(i + prev[0], j + prev[1])
                ? board[i + post[0]][j + post[1]] == type   // 바둑판에서 가로든 세로든 첫 번째 라인
                : board[i + prev[0]][j + prev[1]] != type && board[i + post[0]][j + post[1]] == type; // 그 외 나머지
    }

    private static void findFirstOnes() {
        for (int i = 0; i < 19; i++)
            for (int j = 0; j < 19; j++) {
                char type = board[i][j];
                if (type == '0') continue;

                Queue<int[]> typeQueue = type == '1' ? blacks : whites;
                for (int k = 0; k < 4; k++)
                    // 오목이 완성 되었는지 확인하기 위해 탐색할 방향까지 함께 저장
                    if (isFirst(i, j, type, k)) typeQueue.add(new int[] {i, j, 7 - k});
            }
    }

    private static boolean bfs(char type, int[] start) {
        int[] move = moves[start[2]];
        int count = 1;

        int nr = start[0] + move[0], nc = start[1] + move[1];
        while (isValid(nr, nc)) {
            if (board[nr][nc] != type) break;

            count++;
            nr += move[0];
            nc += move[1];
        }

        return count == 5;
    }

    private static int solution() {
        while (!blacks.isEmpty()) {
            int[] black = blacks.poll();
            boolean isBlackWon = bfs('1', black);
            if (isBlackWon) {
                won = black;
                return 1;
            }
        }

        while(!whites.isEmpty()) {
            int[] white = whites.poll();
            boolean isWhiteWon = bfs('2', white);
            if (isWhiteWon) {
                won = white;
                return 2;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++)
                board[i][j] = st.nextToken().charAt(0);
        }
        findFirstOnes();

        int result = solution();
        bw.write(String.format("%d\n", result));
        if (result != 0) bw.write(String.format("%d %d", won[0] + 1, won[1] + 1));
        bw.flush();
    }
}
