
#include <iostream>
#include <vector>
using namespace std;

vector<pair<int, int>> virus[2001];
int n, k, s, x, y, board[202][202];
int dx[] = {1, -1, 0, 0}, dy[] = {0, 0, 1, -1};
bool visited[202][202];

int main() {
	cin >> n >> k;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++) {
			cin >> board[i][j];
			virus[board[i][j]].push_back(make_pair(i, j));
		}

	cin >> s >> x >> y;
	while (s--) {
		for (int v = 1; v <= k; v++) {
			int v_size = virus[v].size();
			for (int i = 0; i < v_size; i++) {
				int r = virus[v][i].first, c = virus[v][i].second;
				if (visited[r][c]) continue;
				for (int j = 0; j < 4; j++) {
					int nr = r + dx[j], nc = c + dy[j];
					if (nr && nc && nr <= n && nc <= n && !board[nr][nc]) {
						board[nr][nc] = v;
						virus[v].push_back({ nr, nc });
					}
				}
				visited[r][c] = true;
			}
		}
	}
	cout << board[x][y];
}
