#include <iostream>

using namespace std;

int k, n = 1;

int main() {
	cin >> k;

	while (1) {

		n *= 2;

		if (k - n <= 0) break;

		k -= n;
	}
	
	while (n > 1) {
		if (k <= n / 2) cout << '4';
		else {
			k -= (n / 2);
			cout << '7';
		}
		n /= 2;
	}
}