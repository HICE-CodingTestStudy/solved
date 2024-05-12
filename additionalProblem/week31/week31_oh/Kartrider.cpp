
#include <iostream>
#include <queue>
using namespace std;

int n, m, map[301][301], dp[301][301];

void bfs() {
	queue<pair<int, int>> q;
	queue<int> cntq;
	q.push({ 1, 1 });
	cntq.push(0);

	while (!q.empty()) {
		int r = q.front().first, c = q.front().second;
		int cnt = cntq.front();

		q.pop();
		cntq.pop();

		int nr = r + map[r][c], nc = c + map[r][c];
		nr = nr > n ? n : nr;
		nc = nc > m ? m : nc;

		if ((nr == n && c == m) || (r == n && nc == m)) {
			cout << cnt + 1;
			return;
		}

		for (int i = r + 1; i <= nr; i++) {
			if (dp[i][c]) continue;
			q.push({ i, c });
			cntq.push(cnt + 1);
			dp[i][c] = cnt + 1;
		}

		for (int i = c + 1; i <= nc; i++) {
			if (dp[r][i]) continue;
			q.push({ r, i });
			cntq.push(cnt + 1);
			dp[r][i] = cnt + 1;
		}
	}
}

int main() {
	cin >> n >> m;

	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= m; j++)
			cin >> map[i][j];

	bfs();
}
