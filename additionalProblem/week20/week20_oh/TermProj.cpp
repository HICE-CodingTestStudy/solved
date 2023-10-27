
#include <iostream>
#include <queue>
using namespace std;

int t, n, ans, a[100001], cnt[100001];
queue<int> q;	// 팀에 못 들어가는 사람

int main() {
	cin >> t;

	while (t--) {
		fill(cnt, cnt + 100001, 0);
		ans = 0;

		cin >> n;
		for (int i = 1; i <= n; i++) {
			cin >> a[i];
			cnt[a[i]]++;	// 선택된 횟수
		}

		for (int i = 1; i <= n; ++i)
			if (!cnt[i]) q.push(i);
	
		while (!q.empty()) {
			int cur = q.front();
			q.pop();
			ans++;
			if (--cnt[a[cur]] == 0) q.push(a[cur]);	// <선택받지 못한 사람>의 픽이 선택된 횟수가 1이면 팀에 못 들어가므로 q에 추가
		}
		cout << ans << endl;
	}
}
