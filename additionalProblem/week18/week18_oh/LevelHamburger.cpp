
#include <iostream>
using namespace std;
typedef long long ll;

ll N, X, ans, b[51] = { 1 }, p[51] = { 1 };

void f(ll lv, ll len) {
	if (!len)
		return;
	else if (!lv) {
		ans++;
		return;
	}
	
	if (len - 1 == b[lv - 1]) {
		ans += p[lv - 1];
		f(lv - 1, len - b[lv - 1] - 1);
	}

	else if (len - 1 < b[lv - 1])
		f(lv - 1, len - 1);

	else {
		ans += p[lv - 1] + 1;
		f(lv - 1, len - b[lv - 1] - 2);
	}

}

int main() {
	cin >> N >> X;

	for (int i = 1; i <= N; i++) {
		b[i] = b[i - 1] * 2 + 3;
		p[i] = p[i - 1] * 2 + 1;
	}

	f(N, X);
    cout << ans;
}