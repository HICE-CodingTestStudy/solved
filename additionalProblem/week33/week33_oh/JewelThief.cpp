
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int n, k;
long long ans;

int main() {
	cin >> n >> k;

	vector<pair<int, int>> v1(n);
	vector<int> v2(k);
	
	for (int i = 0; i < n; i++)
		cin >> v1[i].first >> v1[i].second;
	
	for (int i = 0; i < k; i++)
		cin >> v2[i];

	sort(v1.begin(), v1.end());
	sort(v2.begin(), v2.end());
	
	priority_queue<int> pq;

	int j = 0;
	for (int i = 0; i < k; i++) {
		while (j < n && v1[j].first <= v2[i]) {
			pq.push(v1[j++].second);
		}
		if (!pq.empty()) {
			ans += pq.top();
			pq.pop();
		}
	}
	
	cout << ans;
}