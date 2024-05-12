
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, m, k, ans, man[1001], woman[1001], dp[1001][1001];

int main() {
	cin >> n >> m;
	for (int i = 1; i <= n; i++)
		cin >> man[i];
	for (int i = 1; i <= m; i++)
		cin >> woman[i];

	sort(man, man + n + 1);
	sort(woman, woman + m + 1);
	
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= m; j++) {
			dp[i][j] = dp[i - 1][j - 1] + abs(man[i] - woman[j]);
			if (i < j)
				dp[i][j] = min(dp[i][j], dp[i][j - 1]);
			else if (i > j)
				dp[i][j] = min(dp[i][j], dp[i - 1][j]);
		}

	cout << dp[n][m];
}
