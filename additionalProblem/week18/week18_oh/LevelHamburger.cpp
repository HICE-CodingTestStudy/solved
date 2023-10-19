
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

	len--;	// 가장 왼쪽 햄버거번 1개 빼주기
	if (len == b[lv - 1]) {
		ans += p[lv - 1];
		f(lv - 1, len - b[lv - 1]);
	}

	else if (len < b[lv - 1])
		f(lv - 1, len);

	else {
		ans += p[lv - 1] + 1;
		f(lv - 1, len - b[lv - 1] - 1);	// 가운데 패티 1개 빼주기
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
