import java.io.*;

public class Solitaire {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private static final int ROW = 5, COL = 9;

    private static char[][] board;
    private static int originPin, minPin, minMove, nMove;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            parseInput();
            movePins();
            printAnswer();
        }
        bw.flush();
    }

    private static void parseInput() {
        try {
            board = new char[ROW][COL];
            minPin = Integer.MAX_VALUE;
            minMove = originPin = nMove = 0;

            for (int r = 0; r < ROW; r++) {
                String line = br.readLine();
                if (line.isEmpty()) line = br.readLine();
                for (int c = 0; c < COL; c++) {
                    board[r][c] = line.charAt(c);
                    if (board[r][c] == 'o') originPin++;
                }
            }
        } catch (IOException ignored) {}
    }

    private static void movePins() {
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                if (board[r][c] != 'o') continue;

                for (int[] move : moves) {
                    int nr = r + move[0], nc = c + move[1];
                    int nnr = nr + move[0], nnc = nc + move[1];

                    if (!isPin(nr, nc) || !isEmpty(nnr, nnc)) continue;

                    board[r][c] = board[nr][nc] = '.';
                    board[nnr][nnc] = 'o';
                    nMove++;
                    movePins();
                    board[r][c] = board[nr][nc] = 'o';
                    board[nnr][nnc] = '.';
                    nMove--;
                }
            }
        }

        int nPin = originPin - nMove;
        if (minPin > nPin) {
            minPin = nPin;
            minMove = nMove;
        }
    }

    private static boolean isPin(int r, int c) {
        return isValid(r, c) && board[r][c] == 'o';
    }

    private static boolean isEmpty(int r, int c) {
        return isValid(r, c) && board[r][c] == '.';
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < ROW && c >= 0 && c < COL;
    }

    private static void printAnswer() {
        try {
            bw.write(minPin + " " + minMove + "\n");
        } catch (IOException ignored) {}
    }
}
