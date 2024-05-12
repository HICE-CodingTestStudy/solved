
#include <iostream>
using namespace std;

int d, n, s, ans, oven[300001];

int main() {
	cin >> d >> n;
	for (int i = 0; i < d; i++) {
		cin >> s;
		if (i) s = min(s, oven[i - 1]);
		oven[i] = s;
	}

	ans = d - 1;
	while (n--) {
		cin >> s;
		if (ans < 0 || s > oven[0]) {
			cout << 0;
			return 0;
		}
		for (int j = ans; j >= 0; j--)
			if (oven[j] >= s) {
				ans = j - 1;
				break;
			}
	}
	cout << ans + 2;
}