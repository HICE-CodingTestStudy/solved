
#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>

using namespace std;

int n, m, r, c, ans;
vector<int> rs, cs;

int main() {
	cin >> n >> m;

	int x, y;
	for (int i = 0; i < m; i++) {
		scanf("%d %d", &x, &y);
		rs.push_back(x);
		cs.push_back(y);
	}

	sort(rs.begin(), rs.end());
	sort(cs.begin(), cs.end());

	r = rs[m / 2];
	c = cs[m / 2];

	for (int i = 0; i < m; i++)
		ans += abs(rs[i] - r) + abs(cs[i] - c);

	cout << ans;
}