#include <iostream>
using namespace std;

int n, m, l;

int main() {
	cin >> n;
	if (!(n % 2 && n % 5))
		return cout << -1, 0;
	
	do {
		m = (10 * m + 1) % n;
		l++;
	} while (m % n);
	cout << l;
}
