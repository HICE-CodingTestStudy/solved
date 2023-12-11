
#include <iostream>
#include <vector>
using namespace std;

struct P {
	int r, c;
	double p;
	P(int r, int c, double p) :r(r), c(c), p(p) {}
	P() {}
};
int n, d, ans, sand[503][503];
int dx[] = { 0, 1, 0, -1 }, dy[] = { -1, 0, 1, 0 };
vector<P> T[4] = {
	{P(-2, 0, 0.02), P(-1, -1, 0.1), P(-1, 0, 0.07), P(-1, 1, 0.01), P(0, -2, 0.05), P(0, -1, 0)},
	{P(0, -2, 0.02), P(1, -1, 0.1), P(0, -1, 0.07), P(-1, -1, 0.01), P(2, 0, 0.05), P(1, 0, 0)},
	{},
	{}
};

void setT() {
	for (int i = 0; i <= 3; i++) {	// T[0], T[1] 나머지 절반 추가
		T[0].push_back(P(-T[0][i].r, T[0][i].c, T[0][i].p));
		T[1].push_back(P(T[1][i].r, -T[1][i].c, T[1][i].p));
	}
	for (int i = 0; i < T[0].size(); i++) { // T[2], T[3] 전체 추가
		T[2].push_back(P(T[0][i].r, -T[0][i].c, T[0][i].p));
		T[3].push_back(P(-T[1][i].r, T[1][i].c, T[1][i].p));
	}
}

void updateSand(int x, int y){
    int subSum = 0;
    for (int k = 0; k < T[0].size(); k++) {    // 주변 이동하는 모래
        int nx = x + T[d][k].r, ny = y + T[d][k].c;
        double p = T[d][k].p;

        int added = int(sand[x][y] * p);
        sand[nx][ny] += added;
        subSum += added;
    }
    sand[x + T[d][5].r][y + T[d][5].c] += sand[x][y] - subSum;    // 남은 모래 더해줌
    sand[x][y] = 0;
}

int main() {
	setT();
	cin >> n;
	for (int i = 2; i <= n + 1; i++)
		for (int j = 2; j <= n + 1; j++)
			scanf("%d", &sand[i][j]);

	// (x, y) : 토네이도 좌표
	int x = n / 2 + 2, y = n / 2 + 2;
	for (int i = 1; i < n; i++) {
		int t = i < n - 1 ? 2 : 3;
		while (t--) {
			for (int j = 1; j <= i; j++) {
				x = x + dx[d], y = y + dy[d];
				updateSand(x, y);
			}
			++d %= 4;
		}
	}

	for (int i = 0; i <= n + 3; i++) {
		ans += sand[0][i] + sand[1][i] + sand[n + 2][i] + sand[n + 3][i];
		if (i >= 2 && i <= n + 1)
			ans += sand[i][0] + sand[i][1] + sand[i][n + 2] + sand[i][n + 3];
	}
	cout << ans;
}
