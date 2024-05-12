
#include <iostream>
using namespace std;

long long n, m, sum, ans, a[500001];
int l, r;

int main() {
	cin >> n >> m;

	for (; r < n; r++) {
    		scanf("%d", a + r);
		sum += a[r];
    		while (sum > m) sum -= a[l++];
		ans = max(ans, sum);
	}
	cout << ans;
}
