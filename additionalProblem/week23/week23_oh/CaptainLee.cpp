
#include <iostream>
using namespace std;

int n, ans, a[123], dp[300001];

int main() {
	fill(dp + 1, dp + 300001, 1e9);
	cin >> n;
	for (int i = 1; i <= 120; i++) {
		int s = 0;
		for (int j = i; j; j--)
			s += j;
		a[i] = a[i - 1] + s;
	}
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= 120; j++)
			if (i - a[j] >= 0) dp[i] = min(dp[i], dp[i - a[j]] + 1);
    
	cout << dp[n];
}
