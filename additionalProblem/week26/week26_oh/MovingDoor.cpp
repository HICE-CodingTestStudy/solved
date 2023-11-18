
#include <iostream>
using namespace std;

int n, t, A, B, s[21], dp[21][21][21];

int f(int a, int b, int cnt) {
	if (cnt == t) return 0;
  
	dp[a][b][cnt] = min(abs(s[cnt] - a) + f(s[cnt], b, cnt + 1),
		abs(s[cnt] - b) + f(a, s[cnt], cnt + 1));
  
	return dp[a][b][cnt];
}

int main() {
	cin >> n >> A >> B >> t;
	for (int i = 0; i < t; i++)
		cin >> s[i];
  
	cout << f(A, B, 0);
}
