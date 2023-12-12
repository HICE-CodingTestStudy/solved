
#include <iostream>
#include <memory.h>
using namespace std;

int n, m, a[1001], dp[1001][1001];

int dfs(int idx, int len) {
	if (idx >= n)
		return 0;

	if (dp[idx][len] != -1)
		return dp[idx][len];

	dp[idx][len] = 1e9;

	dp[idx][len] = (m - len + 1) * (m - len + 1) + dfs(idx + 1, a[idx] + 1);

	if (len + a[idx] <= m)
		dp[idx][len] = min(dp[idx][len], dfs(idx + 1, len + a[idx] + 1));

	return dp[idx][len];
}

int main() {
	cin >> n >> m;

	for (int i = 0; i < n; i++)
		cin >> a[i];

	memset(dp, -1, 1001 * sizeof(dp[0]));

	cout << dfs(0, 0);
}
