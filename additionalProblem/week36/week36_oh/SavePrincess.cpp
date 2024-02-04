
#include <iostream>
#include <tuple>
#include <queue>
#define tup tuple<int, int, int>
using namespace std;

int n, m, t, gx, gy, ans, arr[101][101];
int dx[] = { 0, 0, -1, 1 }, dy[] = { -1, 1, 0, 0 };
bool vis[101][101];

int bfs() {
	for (int u = 1; u <= n; u++)
		for (int v = 1; v <= m; v++)
			vis[u][v] = 0;
	
	queue<pair<tup, bool>> q;
	q.push({ tup(1, 1, 0) , false });
	vis[1][1] = 1;

	while (!q.empty()) {
		tup cur = q.front().first;
		bool gram = q.front().second;
		q.pop();
		int x = get<0>(cur), y = get<1>(cur), c = get<2>(cur);

		if (x == n && y == m)
			return c;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i], ny = y + dy[i];

			if (!nx || !ny || nx > n || ny > m || vis[nx][ny]) continue;

			if (gram) {
				vis[nx][ny] = 1;
				q.push({ tup(nx, ny, c + 1), gram });
			}
			
			else {
				if (arr[nx][ny] == 2) {
					while (!q.empty()) q.pop();
					for (int u = 1; u <= n; u++)
						for (int v = 1; v <= m; v++)
							vis[u][v] = 0;
					vis[nx][ny] = 1;
					q.push({ tup(nx, ny, c + 1), true });
				}
				else if (arr[nx][ny] == 0) {
					vis[nx][ny] = 1;
					q.push({ tup(nx, ny, c + 1), gram });
				}
			}
		}
	}
	return 1e9;
}

int main() {
	cin >> n >> m >> t;

	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= m; j++) {
			cin >> arr[i][j];
			if (arr[i][j] == 2) {
				gx = i, gy = j;
				arr[i][j] = 0;
			}
		}

	ans = bfs();

	arr[gx][gy] = 2;
	ans = min(ans, bfs());

	if (ans > t) cout << "Fail";
	else cout << ans;
}