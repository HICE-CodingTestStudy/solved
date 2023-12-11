
#include <iostream>
using namespace std;

int n, k, i, j, a[11];

int main() {
	cin >> n;
	for (; i < n; i++) {
		cin >> k;
		for (j = 0; k || a[j]; k -= a[j++] == 0);
        a[j] = i + 1;
	}
	for (i = 0; i < n; i++)
		cout << a[i] << " ";
}
