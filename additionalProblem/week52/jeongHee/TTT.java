class Solution {
    static int count(String[] board, char c) {
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == c) cnt++;
            }
        }
        return cnt;
    }
    static boolean checkCount(String[] board) {
        int fir = count(board, 'O');
        int sec = count(board, 'X');
        return fir - sec == 1 || fir - sec == 0;
    }

    static boolean checkValid(String[] board) {
        String sBoard = board[0] + board[1] + board[2];
        if(!checkCount(board)) return false;
        int[][] check = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        boolean fir = false, sec = false;
        for (int i = 0; i < check.length; i++) {
            fir |= sBoard.charAt(check[i][0]) == 'O' && sBoard.charAt(check[i][1]) == 'O' && sBoard.charAt(check[i][2]) == 'O';
            sec |= sBoard.charAt(check[i][0]) == 'X' && sBoard.charAt(check[i][1]) == 'X' && sBoard.charAt(check[i][2]) == 'X';
        }
        if(fir&&sec) return false;
        if(count(board,'O')!=count(board,'X')+1 && fir) return false;
        if(count(board,'O')!=count(board,'X') && sec) return false;
        return true;
    }

    public int solution(String[] board) {
        return checkValid(board) ? 1 : 0;
    }
}