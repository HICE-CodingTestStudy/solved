
#include <iostream>
#include <vector>
using namespace std;

vector<pair<int, int>> v;
int N, L, R, sum, ans, world[52][52];
int dx[] = { -1, 0, 1, 0 };
int dy[] = { 0, 1, 0, -1 };
bool mov, visited[51][51];

void dfs(int x, int y) {
	visited[x][y] = 1;
	sum += world[x][y];
	v.push_back({ x,y });

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i], ny = y + dy[i];
		int diff = world[nx][ny] - world[x][y];
		diff = diff < 0 ? -diff : diff;
		if (world[nx][ny] && !visited[nx][ny] && diff >= L && diff <= R) {
			mov = true;
			dfs(nx, ny);
		}
	}
}

int main() {

	cin >> N >> L >> R;

	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			cin >> world[i][j];

	while (1) {
		mov = false;
		fill(&visited[0][0], &visited[50][51], 0);
		
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				if (!visited[i][j]) {
					v.clear();
					sum = 0;
					dfs(i, j);
					for (int k = 0; k < v.size(); k++)
						world[v[k].first][v[k].second] = sum / v.size();
				}
		if (mov) ans++;
		else break;
	}
	cout << ans;
}
