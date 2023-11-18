
#include <iostream>
using namespace std;

int n, m, ans, map[102][72];
int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 }, dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
bool v[102][72];

int dfs(int x, int y, int t) {
	if (map[x][y] != t) return map[x][y] < t;
	else if (v[x][y]) return 1;
	v[x][y] = 1;

	int ret = 1;
	for (int i = 0; i < 8; i++) {
		int nx = x + dx[i], ny = y + dy[i];
		ret &= dfs(nx, ny, t);
	}
	return ret;
}

int main() {
	cin >> n >> m;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= m; j++)
			cin >> map[i][j];

	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= m; j++)
			if (!v[i][j] && map[i][j])
				ans += dfs(i, j, map[i][j]);
	
	cout << ans;
}
