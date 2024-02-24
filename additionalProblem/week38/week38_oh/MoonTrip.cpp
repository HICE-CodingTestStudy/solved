
#include <iostream>
using namespace std;

int n, m, ans = 1e9, v[1003][1001], dp[1001][1001][3];

int main() {
    cin >> n >> m;

    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < 3; j++)
            dp[i][0][j] = dp[i][m + 1][j] = 1e9;
        for (int j = 1; j <= m; j++) {
            cin >> v[i][j];
            dp[i][j][0] = v[i][j] + min(dp[i - 1][j + 1][1], dp[i - 1][j + 1][2]);
            dp[i][j][1] = v[i][j] + min(dp[i - 1][j][0], dp[i - 1][j][2]);
            dp[i][j][2] = v[i][j] + min(dp[i - 1][j - 1][0], dp[i - 1][j - 1][1]);
        }
    }

    for (int i = 1; i <= m; i++)
        for (int j = 0; j < 3; j++)
            ans = min(ans, dp[n][i][j]);

    cout << ans;
}