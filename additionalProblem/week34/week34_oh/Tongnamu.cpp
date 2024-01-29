
#include <iostream>
#include <algorithm>
using namespace std;

int t, n, k, ans, arr[10001];

int main() {
	cin >> t;

	while (t--) {
		cin >> n;

		for (int i = 0; i < n; i++)
			cin >> arr[i];

		sort(arr, arr + n);

		ans = 0;
		for (int i = 2; i < n; i++)
			ans = max(ans, (arr[i] - arr[i - 2]));

		cout << ans << endl;
	}
}
