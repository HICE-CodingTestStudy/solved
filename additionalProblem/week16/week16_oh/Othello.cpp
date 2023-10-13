
#include <iostream>

using namespace std;

// empty:0	Black:1	White:2
int n, R, C, board[8][8];

int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
bool flag;

void convert(int r, int c, int dir, int color) {

	if (board[r][c] == color || !r || !c) {
		flag = 1;
		return;
	}

	int nr = r + dx[dir], nc = c + dy[dir];
	if (board[nr][nc]) {
		convert(nr, nc, dir, color);
		if (flag) board[r][c] = color;
	}
}

int printMap() {
	
	int res = 0;
	for (int i = 1; i <= 6; i++) {
		for (int j = 1; j <= 6; j++) {
			char c = ' ';
			if (!board[i][j]) c = '.';
			else if (board[i][j] == 1) c = 'B', res++;
			else c = 'W', res--;

			cout << c;
		}
		cout << endl;
	}
	return res;
}

int main() {
	cin >> n;

	board[3][3] = board[4][4] = 2;
	board[3][4] = board[4][3] = 1;

	int bTurn = 1;
	while (n--) {
		cin >> R >> C;

		board[R][C] = bTurn ? 1 : 2;

		for (int i = 0; i < 8; i++) {
			int nr = R + dx[i], nc = C + dy[i];
			if (board[nr][nc] && board[nr][nc] != board[R][C]) {
				flag = 0;
				convert(nr, nc, i, board[R][C]);
			}
		}

		bTurn = !bTurn;
	}

	puts(printMap() > 0 ? "Black" : "White");
}