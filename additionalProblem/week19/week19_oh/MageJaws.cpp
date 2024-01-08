
#include <iostream>
#include <vector>
using namespace std;

int n, m, d, s, sum, A[52][52];
int dx[] = { 0, 0,-1,-1,-1,0,1,1,1 }, dy[] = { 0, -1,-1,0,1,1,1,0,-1 };
int dx4[] = { -1, -1, 1, 1 }, dy4[] = { -1, 1, 1, -1 };
bool c[52][52];
vector<pair<int, int>> cur;

void MoveCloud(int dir, int dis) {
	vector<pair<int, int>> tmp;
	fill(c[0], c[52], false);
	for (int i = 0; i < cur.size(); i++) {
		int nx = cur[i].first + dx[dir] * dis;
		int ny = cur[i].second + dy[dir] * dis;

		while (nx < 1 || ny < 1 || nx > n || ny > n) {
			nx = nx < 1 ? n + nx : nx; nx = nx > n ? nx - n : nx;
			ny = ny < 1 ? n + ny : ny; ny = ny > n ? ny - n : ny;
		}
		A[nx][ny]++;
		tmp.push_back({ nx, ny });
		c[nx][ny] = 1;
	}
	for (int i = 0; i < tmp.size(); i++) {
		int x = tmp[i].first, y = tmp[i].second;
		for (int j = 0; j < 4; j++)
			if (A[x + dx4[j]][y + dy4[j]]) A[x][y]++;
	}
	cur.clear();
}

void NextCloud() {
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			if (A[i][j] > 1 && !c[i][j]) {
				A[i][j] -= 2;
				cur.push_back({ i,j });
			}
}

int main() {
	cin >> n >> m;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			cin >> A[i][j];

	cur.push_back({ n,1 }); cur.push_back({ n,2 });
	cur.push_back({ n - 1,1 }); cur.push_back({ n - 1,2 });
	while (m--) {
		cin >> d >> s;
		MoveCloud(d, s);
		NextCloud();
	}
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			sum += A[i][j];
	cout << sum;
}
