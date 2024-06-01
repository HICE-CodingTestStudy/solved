import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class NewGame {
    private static class Stone {
        int idx, row, col, dir;

        Stone(int idx, int row, int col, int dir) {
            this.idx = idx;
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        public void move(int nr, int nc) {
            this.row = nr;
            this.col = nc;
        }

        private void turn() {
            dir = dir % 2 == 0 ? dir + 1 : dir - 1;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};

    private static Deque<Stone>[][] stonesOnBoard;
    private static Stone[] stones;
    private static char[][] board;
    private static int N, K;

    public static void main(String[] args) throws Exception {
        parseInput();
        int turn = simulation();
        printAnswer(turn);
    }

    private static void parseInput() throws Exception {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        stonesOnBoard = new Deque[N][N];
        board = new char[N][N];
        stones = new Stone[K + 1];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                stonesOnBoard[i][j] = new ArrayDeque<>();
                board[i][j] = input[j].charAt(0);
            }
        }

        for (int k = 1; k <= K; k++) {
            input = br.readLine().split(" ");
            int row = Integer.parseInt(input[0]) - 1;
            int col = Integer.parseInt(input[1]) - 1;
            int dir = Integer.parseInt(input[2]) - 1;
            Stone stone = new Stone(k, row, col, dir);
            stonesOnBoard[row][col].addLast(stone);
            stones[k] = stone;
        }
    }

    private static int simulation() {
        final int MAX = 1000;
        int turn = 1;

        while (turn <= MAX) {
            boolean isOver = false;

            for (int k = 1; k <= K; k++) {
                int r = stones[k].row, c = stones[k].col;
                Stone stone = stonesOnBoard[r][c].peekFirst();
                if (stone.idx != k) continue;

                int nr = r + dx[stone.dir], nc = c + dy[stone.dir];

                if (isCellToTurn(nr, nc)) {
                    nr = r - dx[stone.dir];
                    nc = c - dy[stone.dir];
                    stonesOnBoard[r][c].peekFirst().turn();

                    if (isCellToTurn(nr, nc)) continue;
                    moveStones(r, c, nr, nc);
                } else
                    moveStones(r, c, nr, nc);

                if (stonesOnBoard[nr][nc].size() >= 4) {
                    isOver = true;
                    break;
                }
            }

            if (isOver) break;
            turn++;
        }

        return turn;
    }

    private static boolean isCellToTurn(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N || board[r][c] == '2';
    }

    private static void moveStones(int r, int c, int nr, int nc) {
        while (!stonesOnBoard[r][c].isEmpty()) {
            Stone toMove = board[nr][nc] == '1' ?
                    stonesOnBoard[r][c].pollLast() :
                    stonesOnBoard[r][c].pollFirst();
            toMove.move(nr, nc);
            stonesOnBoard[nr][nc].addLast(toMove);
        }
    }

    private static void printAnswer(int turn) throws Exception {
        bw.write(String.valueOf(turn >= 1000 ? -1 : turn));
        bw.flush();
    }
}
