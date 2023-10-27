
#include <iostream>
#include <vector>
using namespace std;

int R, C, r, c, m, s, d, z, ans;
int dx[] = { 0, -1, 1, 0, 0 }, dy[] = { 0, 0, 0, 1, -1 }, changeD[] = { 0, 2, 1, 4, 3 };
vector<vector<int>> board(102, vector<int>(102, 0));

void moveShark() {
	vector<vector<int>> tmp(102, vector<int>(102, 0));
	for (int i = 1; i <= R; i++)
		for (int j = 1; j <= C; j++)
			if (board[i][j]) {
				s = (board[i][j] % 100000) / 10;
				d = board[i][j] % 10;
				z = board[i][j] / 100000;
				r = i; c = j;
				s %= (d < 3) ? (R - 1) * 2 : (C - 1) * 2;

				while (s--) {
					int nr = r + dx[d], nc = c + dy[d];
					if (nr < 1 || nr > R || nc < 1 || nc > C) {
						d = changeD[d];
						nr = r + dx[d], nc = c + dy[d];
					}
					r = nr, c = nc;
				}
				tmp[r][c] = max((board[i][j] / 10) * 10 + d, tmp[r][c]);
			}
	copy(tmp.begin(), tmp.end(), board.begin());
}

int main() {
	cin >> R >> C >> m;

	while (m--) {
		// s=[1,1000]	d=[1,4]	 z=[1,10000]
		cin >> r >> c >> s >> d >> z;
		board[r][c] = z * 100000 + s * 10 + d;
	}

	for (int i = 1; i <= C; i++) {
		for (int j = 1; j <= R; j++)
			if (board[j][i]) {
				ans += board[j][i] / 100000;
				board[j][i] = 0;
				break;
			}
		moveShark();
	}
	cout << ans;
}
