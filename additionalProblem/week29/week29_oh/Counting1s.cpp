
#include <iostream>
using namespace std;

long long a, b, d[55] = { 1 };

long long cnt1(long long x) {
	long long r = 0;

	for (int i = 54; i >= 0; i--)
		if (x & (1LL << i)) {
			r += d[i - 1] + (x - (1LL << i) + 1);
			x -= 1LL << i;
		}
  
	return r;
}

int main() {
	cin >> a >> b;

	for (int i = 1; i < 55; i++)
		d[i] = d[i - 1] * 2 + (1LL << i);

	cout << cnt1(b) - cnt1(a - 1);
}
