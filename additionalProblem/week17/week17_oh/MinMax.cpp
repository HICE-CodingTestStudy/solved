
#include <iostream>
#include <vector>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

int n, m, a, b, h = 1;
vector<pair<int, int>> tree;

void update(int idx, int val) {
	idx += h;
	tree[idx] = make_pair(val, val);

	while (idx > 1) {
		idx /= 2;
		tree[idx].first = min(tree[idx * 2].first, tree[idx * 2 + 1].first);
		tree[idx].second = max(tree[idx * 2].second, tree[idx * 2 + 1].second);
	}
}

pair<int, int> MinMaxQuery(int node, int l, int r, int Start, int End) {
	if (Start > r || End < l) return make_pair(1e9 + 1, 0);
	else if (l >= Start && r <= End) return tree[node];
	else {
		int mid = (l + r) / 2;
		pair<int, int> lc, rc;
		lc = MinMaxQuery(node * 2, l, mid, Start, End);
		rc = MinMaxQuery(node * 2 + 1, mid + 1, r, Start, End);

		return make_pair(min(lc.first, rc.first), max(lc.second, rc.second));
	}
}

int main() {
  FIO;
	cin >> n >> m;

	while (n > h) h *= 2;
	tree.resize(h * 2);
	fill(tree.begin(), tree.end(), make_pair(1e9 + 1, 0));

	for (int i = 0; i < n; i++) {
		cin >> a;
		update(i, a);
	}
	
	for (int i = 0; i < m; i++) {
		cin >> a >> b;

		pair<int, int> ans = MinMaxQuery(1, 1, h, a, b);

		cout << ans.first << " " << ans.second << '\n';
	}
}
