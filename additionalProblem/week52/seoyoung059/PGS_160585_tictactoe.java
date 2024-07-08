package fin.HICE.week52.seoyoung059;

public class PGS_160585_tictactoe {

    public int solution(String[] board) {
        int answer = 1;

        int cntO=0, cntX=0;

        int[][] check = new int[3][3];
        char curr; int cnt = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                switch(board[i].charAt(j)){
                    case 'O':
                        cnt++;
                        break;
                    case 'X':
                        cnt--;
                        break;
                }
            }

            // 가로
            if(board[i].equals("OOO")) {
                cntO++;
                for(int j=0; j<3; j++){
                    check[i][j]++;
                }
            } else if (board[i].equals("XXX")) {
                cntX++;
                for(int j=0; j<3; j++){
                    check[i][j]--;
                }
            }

            // 세로
            if(board[0].charAt(i)!='.'){
                if(board[0].charAt(i)==board[1].charAt(i) && board[0].charAt(i)==board[2].charAt(i)){
                    if(board[0].charAt(i)=='O'){
                        cntO++;
                        for(int j=0; j<3; j++){
                            check[j][i]++;
                        }
                    } else {
                        cntX++;
                        for(int j=0; j<3; j++){
                            check[j][i]--;
                        }
                    }
                }
            }


        }

        // 대각선
        if(board[1].charAt(1)!='.') {
            if(board[0].charAt(0)==board[1].charAt(1) && board[1].charAt(1)==board[2].charAt(2)){
                if(board[1].charAt(1)=='O'){
                    cntO++;
                    for(int i=0; i<3; i++){
                        check[i][i]++;
                    }
                } else {
                    cntX++;
                    for(int i=0; i<3; i++){
                        check[i][i]--;
                    }
                }
            }


            if(board[2].charAt(0)==board[1].charAt(1) && board[1].charAt(1)==board[0].charAt(2)){
                if(board[1].charAt(1)=='O'){
                    cntO++;
                    for(int i=0; i<3; i++){
                        check[2-i][i]++;
                    }
                } else {
                    cntX++;
                    for(int i=0; i<3; i++){
                        check[2-i][i]--;
                    }
                }
            }
        }

        System.out.println(cntO+" "+cntX+" "+cnt);
        if(cnt<0|| cnt>1 || (cntO>0 && cnt!=1) || (cntX>0 && cnt!=0) ||(cntO>0&&cntX>0)) return 0;

        cnt=0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++) {
                if(check[i][j]>1 || check[i][j]<-1) cnt++;
                if(cnt > 1) return 0;
            }
        }

        return answer;
    }
}
