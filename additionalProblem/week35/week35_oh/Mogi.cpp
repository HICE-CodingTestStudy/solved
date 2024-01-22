
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, l, r, ans, cnt;

vector<pair<int, int>> v;
vector<int> vcnt[1000001];

int main() {
	cin >> n;

	for (int i = 0; i < n; i++) {
		scanf("%d %d", &l, &r);
		v.push_back({ l, 1 });
		v.push_back({ r, -1 });
	}
	sort(v.begin(), v.end());

	for (int i = 0; i < v.size(); i++) {
		cnt += v[i].second;

		if (v[i].second < 0) vcnt[cnt + 1].push_back(v[i].first);
		else vcnt[cnt].push_back(v[i].first);

		ans = max(ans, cnt);
	}

	l = vcnt[ans][0];
	vcnt[ans].push_back(0);

	for (int i = 1; i < vcnt[ans].size(); i++) {
		if (vcnt[ans][i] == vcnt[ans][i + 1]) i++;
		else {
			r = vcnt[ans][i];
			break;
		}
	}

	cout << ans << endl << l << " " << r;
}
