#include <string>
#include <vector>

using namespace std;
int mapSize, res = 1e9;
int dx[]={0,1,0,-1};
int dy[]={1,0,-1,0};

bool checkBoundary(int i, int j){
    if (i >= 0 && j >= 0 && i < mapSize && j < mapSize)
        return true;
    else
        return false;
}

void dfs(vector<vector<int>>& board, vector<vector<bool>>& visited, vector<vector<int>>& cost, int r, int c, int dir, int price){
    
    if (price > cost[r][c]) return;
    if (price < cost[r][c]) cost[r][c] = price;
    
    if (r == mapSize - 1 && c == mapSize - 1){
        res = res < price ? res : price;
        return;
    }
    
    for(int k = 0; k < 4; k++){
        int nr = r + dx[k];
        int nc = c + dy[k];
        if (nr >= 0 && nc >= 0 && nr < mapSize && nc < mapSize && !board[nr][nc] && !visited[nr][nc]){
            visited[nr][nc] = true;
            if (dir == k || (!r && !c)) dfs(board, visited, cost, nr, nc, k, price + 100);
            else dfs(board, visited, cost, nr, nc, k, price + 600);
            
            visited[nr][nc] = false;
        }
    }
}


int solution(vector<vector<int>> board) {
    
    vector<vector<bool>> visited(board.size(), vector<bool>(board.size(),false));
    vector<vector<int>> cost(board.size(), vector<int>(board.size(), 1e9));
 
    visited[0][0] = true;
    mapSize = board.size();
    board[0][0] = 1;
    dfs(board, visited, cost, 0, 0, 0, 0);
    
    return res;
}
