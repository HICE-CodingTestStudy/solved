
#include <iostream>
#include <queue>
using namespace std;

struct Point {
	int x, y;
	Point(int x, int y) :x(x), y(y) {}
	Point() {}
};

int n, m, x, y, dp[51][51][65];
int dx[] = { 0,0,-1,1 }, dy[] = { -1,1,0,0 };
char Map[52][52];
queue<pair<Point, int>> q;
queue<int> c;

void bfs() {
	while (!q.empty()) {
		Point p = q.front().first;
		int bitMask = q.front().second;
		int cnt = c.front();
		q.pop(); c.pop();

		for (int i = 0; i < 4; i++) {
			int nx = p.x + dx[i], ny = p.y + dy[i];

			if (!nx || !ny || nx > n || ny > m || dp[nx][ny][bitMask])
        continue;
			
      dp[nx][ny][bitMask] = cnt + 1;
			char next = Map[nx][ny];
            
			// 출구
			if (next == '1') {
				cout << cnt + 1;
				return;
			}

			// 벽
			else if (next == '#') continue;

			// 문
			else if (next >= 'A' && next <= 'F') {
				if (!(bitMask & (1 << next - 'A'))) continue;
				
				q.push({ Point(nx, ny), bitMask });
				c.push(cnt + 1);
			}

			// 열쇠
			else if (next >= 'a' && next <= 'f') {
				q.push({ Point(nx, ny), (bitMask | (1 << next - 'a')) });
				c.push(cnt + 1);
			}

			// 빈 칸
			else {
				q.push({ Point(nx, ny), bitMask });
				c.push(cnt + 1);
			}
		}
	}
	cout << -1;
	return;
}

int main() {
	cin >> n >> m;

	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= m; j++) {
			cin >> Map[i][j];
			if (Map[i][j] == '0') {
				q.push({ Point(i, j), 0 });
				c.push(0);
				Map[i][j] = '.';
			}
		}
	bfs();
}
