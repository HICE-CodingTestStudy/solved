
#include <iostream>
using namespace std;

string s;
long long dp[41] = { 1, 1 };

int main() {
	cin >> s;
	int l = s.size();
	s = " " + s;
	s[1] -= '0';
  
	for (int i = 2; i <= l; i++) {
		s[i] -= '0';
		int num = s[i - 1] * 10 + s[i];
		if (s[i]) dp[i] += dp[i - 1];
		if (num < 35 && s[i - 1]) dp[i] += dp[i - 2];
	}

	cout << dp[l];
}
