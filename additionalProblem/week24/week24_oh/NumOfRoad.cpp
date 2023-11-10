
#include <iostream>
using namespace std;

long long n, m, k, dp[101][101];
bool cannot[201][201];

int main() {
	cin >> n >> m >> k;

	int a, b, c, d;
	while (k--) {
		cin >> a >> b >> c >> d;
		cannot[a + c][b + d] = 1;
	}
	dp[0][0] = 1;

	for (int i = 1; i <= n; i++) {
		if (cannot[2 * i - 1][0]) break;
		dp[i][0] = 1;
	}

	for (int i = 1; i <= m; i++) {
		if (cannot[0][2 * i - 1]) break;
		dp[0][i] = 1;
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			if (!cannot[2 * i - 1][2 * j])	// 현재 위치의 왼쪽 도로(가로)가 공사일 때
				dp[i][j] += dp[i - 1][j];
			if (!cannot[2 * i][2 * j - 1])	// 현재 위치의 위쪽 도로(세로)가 공사일 때
				dp[i][j] += dp[i][j - 1];
		}
	}

	cout << dp[n][m];
}
