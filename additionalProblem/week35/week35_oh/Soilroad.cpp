
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, l, k, cur, len, ans;
vector<pair<int, int>> v;

int main() {
	cin >> n >> l;
    vector<pair<int, int>>v(n);
    
	for (int i = 0; i < n; i++)
		cin >> v[i].first >> v[i].second;

	sort(v.begin(), v.end());

	for (int i = 0; i < n; i++) {
		cur = max(v[i].first, cur);
		len = v[i].second - cur;
		k = len / l + (len % l > 0);
		ans += k;
		cur += k * l;
	}
	cout << ans;
}
