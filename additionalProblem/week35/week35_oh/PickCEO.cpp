
#include <bits/stdc++.h>
using namespace std;

int n, a, b, ans = 100, score[55];
vector<int> v[55];
bool chk[55];

void bfs(int idx) {
	queue<pair<int, int>> q;
	q.push({idx, 0});
	int cnt = 0;
	while (!q.empty()) {
		int x = q.front().first, c = q.front().second;
		q.pop();
        cnt += !chk[x];
        chk[x] = 1;

		if (cnt == n) {
			score[idx] = c;
			ans = min(ans, c);
			return;
		}

		for (int i = 0; i < v[x].size(); i++)
			if (!chk[v[x][i]])
				q.push({ v[x][i], c + 1 });
	}
}

int main() {
	cin >> n;
	memset(score, 100, (n + 1) * sizeof(int));

	while (1) {
		cin >> a >> b;
		if (a < 0) break;
		v[a].push_back(b);
		v[b].push_back(a);
	}

	for (int i = 1; i <= n; i++) {
		fill(chk, chk + n + 1, 0);
		bfs(i);
	}
	
	vector<int> res;
	for (int i = 1; i <= n; i++) 
		if (ans == score[i]) res.push_back(i);

	cout << ans << " " << res.size() << endl;
	for (auto k : res)
		cout << k << " ";
}
