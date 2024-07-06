#include <string>
#include <vector>
#include <cmath>
using namespace std;

bool isEnd(vector<string> board) {
    for(int i = 0; i < 3; i++) { 
        if(board[i][0] != '.' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true;
        else if(board[0][i] != '.' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;
    }
    if(board[0][0] != '.' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true;
    else if(board[0][2] != '.' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;
    return false;
}

int foo(vector<string> board, vector<string> sample, int turn) {
    int ans = 1;
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            ans &= (board[i][j] == sample[i][j]);
        }
    }
    if(ans == 1 || isEnd(sample)) return ans;
    
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            if(sample[i][j] != '.') continue;
            if(turn == 0) {
                sample[i][j] = 'O';
                ans = max(ans, foo(board, sample, !turn));
            }
            else if(turn == 1) {
                sample[i][j] = 'X';
                ans = max(ans, foo(board, sample, !turn));
            }        
            sample[i][j] = '.';
        }
    }
    return ans;
}

int solution(vector<string> board) {
    vector<string> sample;
    for(int i = 0; i < 3; i++) {
        sample.push_back("...");
    }
    return foo(board, sample, 0);
}