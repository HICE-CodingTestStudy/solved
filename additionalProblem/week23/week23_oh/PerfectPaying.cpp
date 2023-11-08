
#include <iostream>
typedef long long ll;
using namespace std;

ll n, a_gcd, b_gcd, x, y, a, b;

ll GCD(ll a, ll b) {	// 최대공약수
	if (!b) return a;
	else return GCD(b, a % b);
}

ll LCM(ll a, ll b) {	// 최소공배수
	return a * (b / GCD(a, b));
}

pair<ll, ll> f(ll a, ll b) {    // 기약분수
	ll k = GCD(a, b);
	return { a / k, b / k };
}

int main() {
	cin >> n;
	pair<ll, ll> tmp[51];

	cin >> a >> b;
	tmp[0] = f(a, b);
	x = tmp[0].first, y = tmp[0].second;
	for (int i = 1; i < n; i++) {
		cin >> a >> b;
		tmp[i] = f(a, b);
		x = GCD(x, tmp[i].first);
		y = LCM(y, tmp[i].second);
	}
	cout << x << " " << y;
}
