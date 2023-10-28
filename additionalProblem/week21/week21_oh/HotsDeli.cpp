
#include <iostream>
#include <algorithm>
#define MOD 1000000007
using namespace std;

long long n, ans, p, m, a[300002], pow2[300002];

int main() {
	cin >> n;
	for (int i = 0; i < n; i++)
		cin >> a[i];

	sort(a, a + n);

  	// pow2[] = {1-1, 2-1, 4-1, 8-1, 16-1, ... }      // -1 : 전체가 빠지는 경우 제외
	int x = 1;
	for (int i = 0; i < n; i++) {
		pow2[i] =  x - 1;
		x *= 2;
		x %= MOD;
	}
	
	for (int i = n - 1; i > 0; i--) {
		p += pow2[i] * a[i];
		m += pow2[i] * a[n - 1 - i];
		p %= MOD; m %= MOD;
	}

	cout << (p % MOD + MOD - m % MOD) % MOD;
}
