
#include <iostream>
#include <queue>
#include <map>
using namespace std;

int k, table[3][3];
int dx[] = { -1,1,0,0 }, dy[] = { 0,0,-1,1 };
map<int, int> m;

pair<int, int> findZero(int t) {
	int d = 100000000;
	for (int i = 0; i < 9; i++) {
		int e = t / d;
		t %= d;
		d /= 10;
		if (!e) return { i / 3, i % 3 };
	}
}

int changeNum(int n, int x, int y, int nx, int ny) {
	int ret = 0, tmp[9];
	for (int i = 8; i >= 0; i--) {
		tmp[i] = n % 10;
		n /= 10;
	}
	swap(tmp[x * 3 + y], tmp[nx * 3 + ny]);

	for (int i = 0; i < 9; i++) {
		ret *= 10;
		ret += tmp[i];
	}
	return ret;
}

void bfs() {
	for (int i = 0; i < 9; i++) {
		k *= 10;
		k += table[i / 3][i % 3];
	}

	if (k == 123456780) {
		cout << 0;
		return;
	}

	m[k] = 1;

	queue<int> q, c;
	q.push(k); c.push(0);

	while (!q.empty()) {
		int n = q.front(), cnt = c.front();
		q.pop(); c.pop();

		int x, y;
		pair<int, int> p = findZero(n);
		x = p.first, y = p.second;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i], ny = y + dy[i];

			if (nx < 0 || nx > 2 || ny < 0 || ny > 2) continue;
			
			int num = changeNum(n, x, y, nx, ny);
			if (m[num]) continue;

			if (num == 123456780) {
				cout << cnt + 1;
				return;
			}

			q.push(num);
			c.push(cnt + 1);
			m[num] = cnt + 1;
		}
	}
	cout << -1;
}

int main() {
	for (int i = 0; i < 3; i++)
		for (int j = 0; j < 3; j++)
			cin >> table[i][j];

	bfs();
}
