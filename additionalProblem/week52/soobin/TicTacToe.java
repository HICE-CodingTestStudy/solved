public class TicTacToe {
    private boolean isPossible;
    private char[][] compare = new char[3][3];

    public int solution(String[] compare) {
        char[][] newBoard = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                this.compare[i][j] = compare[i].charAt(j);
                newBoard[i][j] = '.';
            }

        if (check(newBoard)) isPossible = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isPossible) return 1;
                newBoard[i][j] = 'O';
                doGame(newBoard, 'X');
                newBoard[i][j] = '.';
            }
        }

        return 0;
    }

    private void doGame(char[][] board, char type) {
        if (check(board)) isPossible = true;
        if (isPossible) return;
        if (isEnd(board)) return;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != '.') continue;

                board[i][j] = type;
                doGame(board, type == 'O' ? 'X' : 'O');
                board[i][j] = '.';
            }
        }
    }

    private boolean check(char[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (this.compare[i][j] != board[i][j]) return false;
        return true;
    }

    private boolean isEnd(char[][] board) {
        // 가로 체크
        for (int i = 0; i < 3; i++) {
            if (board[i][0] =='.') continue;
            char type = board[i][0];
            int n = 1;
            for (int j = 1; j < 3; j++)
                if (board[i][j] == type) n++;
            if (n == 3) return true;
        }

        // 세로 체크
        for (int i = 0; i < 3; i++) {
            if (board[0][i] =='.') continue;
            char type = board[0][i];
            int n = 1;
            for (int j = 1; j < 3; j++)
                if (board[j][i] == type) n++;
            if (n == 3) return true;
        }

        // 대각선 체크
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '.') return true;
        if (board[0][2] == board[1][1] && board[2][0] == board[1][1] && board[0][2] != '.') return true;

        return false;
    }
}
