
#include <iostream>
#include <vector>
using namespace std;

int n, m, X, Y, ans, b[1000][1000];
int dx[] = { -1,1,0,0 }, dy[] = { 0,0, -1,1 };
bool cycle, safe[1000][1000];
vector<pair<int, int>> v;

int convertChar(char c) {
	if (c == 'U') return 0;
	else if (c == 'D') return 1;
	else if (c == 'L') return 2;
	else if (c == 'R') return 3;
}

void dfs(int x, int y) {
	for (int i = 0; i < v.size(); i++) {
		if (v[i] == make_pair(x, y)) { // 이미 방문경로에 들어있는 경우 사이클
			ans++;
			cycle = 1;
			return;
		}
		else if (safe[v[i].first][v[i].second]) { // 세이프존 갈 수 있는 경우
			cycle = 1;
			return;
		}
	}
	v.push_back({ x, y });

	int nx = x + dx[b[x][y]], ny = y + dy[b[x][y]];
	dfs(nx, ny);
}

int main() {
	cin >> n >> m;
	string s;
	for (int i = 0; i < n; i++) {
		cin >> s;
		for (int j = 0; j < m; j++)
			b[i][j] = convertChar(s[j]);
	}

	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			if (!safe[i][j]) {
				X = i, Y = j, cycle = 0;
				v.clear();
				dfs(i, j);
				if (cycle) {
					for (int k = 0; k < v.size(); k++)
						safe[v[k].first][v[k].second] = 1;
				}
			}
	cout << ans;
}
