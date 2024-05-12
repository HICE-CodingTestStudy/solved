
#include <iostream>
#include <queue>
using namespace std;

int n, m, x, y, mov[101];
bool visited[101];

void bfs() {
	queue<pair<int, int>> q;
	q.push({ 1,0 });
	visited[1] = 1;

	while (!q.empty()) {
		int cur = q.front().first;
		int cnt = q.front().second;
		q.pop();
		visited[cur] = 1;

		if (cur == 100) {
			cout << cnt;
			return;
		}

		for (int i = 1; i <= 6; i++) {
			if (mov[cur + i])
				q.push({ mov[cur + i], cnt + 1 });
			else if (!visited[cur + i])
				q.push({ cur + i, cnt + 1 });
		}
	}
}

int main() {
	cin >> n >> m;

	for (int i = 0; i < n + m; i++) {
		cin >> x >> y;
		mov[x] = y;
	}
	bfs();
}
