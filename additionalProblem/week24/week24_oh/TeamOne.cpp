
#include <iostream>
#include <vector>
using namespace std;

int n, m, o, p, ans = 15;
vector<int> team, v[11];

void f(int idx, int cnt) {
	if (cnt) {
		bool solved[11] = { false };
		for (int i = 0; i < cnt; i++)
			for (int j = 0; j < v[team[i]].size(); j++)
				solved[v[team[i]][j]] = 1;
			
		bool flag = true;
		for (int i = 1; i <= n; i++) 
			flag = solved[i] && flag;
		
		if (flag) ans = min(ans, cnt);
	}

	for (int i = idx; i <= m; i++) {
		team.push_back(i);
		f(i + 1, cnt + 1);
		team.pop_back();
	}
}

int main() {
	cin >> n >> m;
  
	for (int i = 1; i <= m; i++) {
		cin >> o;
		for (int j = 0; j < o; j++) {
			cin >> p;
			v[i].push_back(p);
		}
	}

	f(1, 0);

	ans > 10 ? cout << -1 : cout << ans;
}
