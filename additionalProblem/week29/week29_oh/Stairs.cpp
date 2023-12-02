
#include <iostream>
using namespace std;

int n, h, ans = 1, dp[100001];

int main() {
	cin >> n;

	for (int i = 1; i <= n; i++) {
		cin >> h;
		dp[i] = min(h, dp[i - 1] + 1);
		ans = max(ans, dp[i]);
	}

	cout << ans;
}
