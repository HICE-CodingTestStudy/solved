#include <iostream>
using namespace std;

long long n, ans;
int main() {
	cin >> n;
	long long d, x = 0, y = n / 2 - 1, r = n * n / 4;
    
	while (x <= n && y >= 0) {
		d = (x + 1) * (x + 1) + y * y;
        x += d <= r;
        y -= d >= r;
		ans++;
	}
	cout << ans * 4;
}