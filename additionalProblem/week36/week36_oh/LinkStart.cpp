
#include <iostream>
using namespace std;

int n, ans = 1e9, arr[21][21];
bool vis[21];

int calc() {
	int s = 0, l = 0;

	for(int i=1;i<=n;i++)
		for (int j = 1; j <= n; j++) {
			if (vis[i] && vis[j])
				s += arr[i][j];
			if (!vis[i] && !vis[j])
				l += arr[i][j];
		}

	return abs(s - l);
}

void dfs(int idx, int d) {
	if (d && d <= n / 2)
		ans = min(ans, calc());

	for (int i = idx; i <= n; i++) {
		vis[i] = 1;
		dfs(i + 1, d + 1);
		vis[i] = 0;
	}
}

int main() {
	cin >> n;

	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			cin >> arr[i][j];

	dfs(1, 0);
	cout << ans;
}