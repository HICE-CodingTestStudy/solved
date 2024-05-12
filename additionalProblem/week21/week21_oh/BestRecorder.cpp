
#include <iostream>
using namespace std;

int n, m, ans, b[1000][1000], v[1000][1000];
int dx[] = { -1,1,0,0 }, dy[] = { 0,0, -1,1 };

int dtoi(char c) {
	if (c == 'U') return 0;
	else if (c == 'D') return 1;
	else if (c == 'L') return 2;
	else if (c == 'R') return 3;
}

void dfs(int x, int y) {
	v[x][y] = -1;
	int nx = x + dx[b[x][y]], ny = y + dy[b[x][y]];
    
	if (v[nx][ny] == -1) {
		ans++;
		v[x][y] = 1;
		return;
	}
	if (!v[nx][ny]) dfs(nx, ny);
	v[x][y] = 1;
}

int main() {
	cin >> n >> m;
	string s;
	for (int i = 0; i < n; i++) {
		cin >> s;
		for (int j = 0; j < m; j++)
			b[i][j] = dtoi(s[j]);
	}

	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			if (!v[i][j]) dfs(i, j);

	cout << ans;
}
