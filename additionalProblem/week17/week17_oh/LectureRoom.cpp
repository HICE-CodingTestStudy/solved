
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, x, s, e, tmp, ans;
vector<pair<int, int>> v;

int main() {
	cin >> n;
	
	while (n--) {
		cin >> x >> s >> e;

		v.push_back({ s, 1 });
		v.push_back({ e, -1 });
	}

	sort(v.begin(), v.end());

	for (auto [a,b] : v) {
		tmp += b;
		ans = max(ans, tmp);
	}

	cout << ans;
}
