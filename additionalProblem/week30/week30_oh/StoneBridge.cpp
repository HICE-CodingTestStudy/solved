
#include <iostream>
using namespace std;

int dp[2][101], k=0;
string s, b[2];

int main() {
	cin >> s >> b[0] >> b[1];
	dp[0][0] = dp[1][0] = 1;

	for (int i = 0; i < b[0].size(); i++)
		for (int j = s.size() - 1; j >= 0; j--) {
			if (s[j] == b[0][i]) dp[1][j + 1] += dp[0][j];
			if (s[j] == b[1][i]) dp[0][j + 1] += dp[1][j];
		}
  
	cout << dp[0][s.size()] + dp[1][s.size()];
}
