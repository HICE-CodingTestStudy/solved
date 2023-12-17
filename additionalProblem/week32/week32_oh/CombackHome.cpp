
#include <iostream>
using namespace std;

int r, c, k, ans, v[6][6];
int dx[] = { -1, 1, 0, 0 }, dy[] = { 0, 0, -1, 1 };
char map[6][6];

void dfs(int x, int y, int d) {
	if (d == k && x == 1 && y == c) {
		ans++;
		return;
	}
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i], ny = y + dy[i];
		if (v[nx][ny] || map[nx][ny] == 'T' || !nx || !ny || nx > r || ny > c) continue;
		v[nx][ny] = 1;
		dfs(nx, ny, d + 1);
		v[nx][ny] = 0;
	}
}

int main() {
	cin >> r >> c >> k;
	for (int i = 1; i <= r; i++)
		for (int j = 1; j <= c; j++)
			cin >> map[i][j];

	v[r][1] = 1;
	dfs(r, 1, 1);
	cout << ans;
}