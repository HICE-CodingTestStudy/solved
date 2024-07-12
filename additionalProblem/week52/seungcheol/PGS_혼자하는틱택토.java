class Solution {
    public int solution(String[] board) {
        int first = 0;
        int second = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                char c = board[i].charAt(j);
                if(c == 'O'){
                    first++;
                } else if(c == 'X'){
                    second++;
                }
            }
        }

        //틱택토를 선공 5 : 후공 4만 가능함
        //선공은 최대 2개의 줄을 만들 수 있음 
        //0 0 0
        //0 X X
        //0 X X
        //완성된 것이 있는지 확인
        int cntO = 0;
        int cntX = 0;
        for(int i = 0; i < 3; i++){
            String row = board[i];
            //가로로 일렬 확인
            if(row.charAt(0) == 'O' 
               && row.charAt(1) == 'O' 
               && row.charAt(2) == 'O'){
                cntO++;
            }
            if(row.charAt(0) == 'X' 
               && row.charAt(1) == 'X' 
               && row.charAt(2) == 'X'){
                cntX++;
            }
            //세로로 일렬 확인
            if(board[0].charAt(i) == 'O'
              && board[1].charAt(i) == 'O'
              && board[2].charAt(i) == 'O') {
                cntO++;
            }
            if(board[0].charAt(i) == 'X'
              && board[1].charAt(i) == 'X'
              && board[2].charAt(i) == 'X') {
                cntX++;
            }
        }
        //대각선
        if((board[0].charAt(0) == 'O'
          && board[1].charAt(1) == 'O'
          && board[2].charAt(2) == 'O') ||
          (board[0].charAt(2) == 'O'
          && board[1].charAt(1) == 'O'
          && board[2].charAt(0) == 'O')){
            cntO++;
        }
        if((board[0].charAt(0) == 'X'
          && board[1].charAt(1) == 'X'
          && board[2].charAt(2) == 'X') ||
          (board[0].charAt(2) == 'X'
          && board[1].charAt(1) == 'X'
          && board[2].charAt(0) == 'X')){
            cntX++;
        }

        //불가능하면 true
        //후공이 더 많이 놨으면 안됨
        boolean flag = first < second ;
        //선공이 5개 이상 놓을 수 없음
        flag |= first > 5; 
        //후공이 4개 이상 놓을 수 없음
        flag |= second > 4; 
        //선공과 후공의 돌 개수 차이는 1이 최대임
        flag |= Math.abs(first - second) > 1;
        //후공은 1개이상, 선공은 2개이상의 완성이 나올 수 없음
        flag |= cntX > 1 || cntO > 2;
        //선공이 완료된 상태에서 후공이 나올 수 없음.
        flag |= cntO != 0 && cntX != 0;
        //돌 개수가 다른데 후공이 완성될 수 없음
        flag |= first != second && cntX >= 1;
        //돌 개수 차이가 1개가 아닌데 완성될 수 없음 
        flag |= first - 1 != second && cntO >= 1;
        System.out.println("cntO : " + cntO);
        System.out.println("cntX : " + cntX);
        System.out.println("first : " + first);
        System.out.println("second : " + second);


        if(flag) return 0;
        return 1;
    }
}