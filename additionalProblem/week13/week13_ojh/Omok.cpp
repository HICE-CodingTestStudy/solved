
#include <iostream>

using namespace std;

int board[20][20];

// 상, 우, 하, 좌, 북서, 북동, 남동, 남서
int dx[8] = { -1, 0, 1, 0, -1, -1, 1, 1 };
int dy[8] = { 0, 1, 0, -1, -1, 1, 1, -1 };

int dfs(int stone, int row, int col, int nextRow, int nextCol, int dir, int cnt) {

	if (cnt == 5 && stone != board[nextRow][nextCol])
		return 1;

	if (board[nextRow][nextCol] == stone)
		return dfs(stone, nextRow, nextCol, nextRow + dx[dir], nextCol + dy[dir], dir, cnt + 1);
	
	return 0;
}

int main() {
	
	for (int i = 1; i <= 19; i++)
		for (int j = 1; j <= 19; j++)
			cin >> board[i][j];

	int flag = 0;

	// 왼쪽 우선, 같은 세로줄인 경우 위쪽 우선
	for (int j = 1; j <= 19; j++)
		for (int i = 1; i <= 19; i++)
			if (board[i][j])
				for (int k = 0; k < 8; k++) {
					int nexti = i + dx[k], nextj = j + dy[k];
					if (board[i][j] == board[nexti][nextj]) {
						flag = dfs(board[i][j], i, j, nexti, nextj, k, 1);
						// 6목 이상인 경우 처리
						if (flag && board[i][j] != board[i - dx[k]][j - dy[k]]) {
							cout << board[i][j] << endl << i << ' ' << j;
							exit(0);
						}
					}
				}
	
	cout << 0;
}
