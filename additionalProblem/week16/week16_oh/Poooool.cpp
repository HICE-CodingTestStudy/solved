
#include <iostream>
#include <vector>
using namespace std;

vector<pair<int, int>> v;
int N, M, ans, pool[52][52];
int dx[] = { -1, 0, 1, 0 };
int dy[] = { 0, 1, 0, -1 };
bool can, visited[51][51];
int subMax, totalMax;

void dfs(int x, int y, int h) {

	if (!pool[x][y]) {
		can = 0;
		return;
	}
	v.push_back({ x, y });
	visited[x][y] = 1;

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i], ny = y + dy[i];

		if (pool[nx][ny] > h)
			subMax = min(subMax, pool[nx][ny]);	// 현재보다 크면서 가장 작은 값이 물의 높이
		else if (!visited[nx][ny]) {
			dfs(nx, ny, h);
			if (!can) return;
		}
	}
}

int main() {
	cin >> N >> M;

	string s;
	for (int i = 1; i <= N; i++) {
		cin >> s;
		for (int j = 1; j <= M; j++) {
			pool[i][j] = s[j - 1] - '0';
			totalMax = max(totalMax, pool[i][j]);
		}
	}

	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= M; j++)
			if (pool[i][j] < totalMax) {
				fill(&visited[0][0], &visited[50][51], false);
				v.clear();
				subMax = totalMax;
				can = 1;
				dfs(i, j, pool[i][j]);
				if (can)
					for (int k = 0; k < v.size(); k++) {
						ans += subMax - pool[v[k].first][v[k].second];
						pool[v[k].first][v[k].second] = subMax;
					}
			}
	cout << ans;
}
