
#include <iostream>
#include <queue>
using namespace std;

int t, n, ans, a[100001], cnt[100001];
queue<int> q;

int main() {
	cin >> t;

	while (t--) {
		fill(cnt, cnt + 100001, 0);
		ans = 0;

		cin >> n;
		for (int i = 1; i <= n; i++) {
			cin >> a[i];
			cnt[a[i]]++;
		}

		for (int i = 1; i <= n; ++i)
			if (!cnt[i]) q.push(i);
	
		while (!q.empty()) {
			int cur = q.front();
			q.pop();
			ans++;
			if (--cnt[a[cur]] == 0) q.push(a[cur]);
		}
		cout << ans << endl;
	}
}
