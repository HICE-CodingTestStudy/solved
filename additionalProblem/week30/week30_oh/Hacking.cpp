
#include <iostream>
#include <vector>
using namespace std;

int n, m, a, b, maxl, t[10001];
vector<int> v[10001];
bool visited[10001];

int dfs(int x) {
	visited[x] = 1;
	int ret = 1;

	for (int k: v[x])
		if (!visited[k])
			ret += dfs(k);

	return ret;
}

int main() {
	cin >> n >> m;

	while (m--) {
		cin >> a >> b;
		v[b].push_back(a);
	}

	for (int i = 1; i <= n; i++) {
		fill(visited, visited + 10001, 0);
		t[i] = dfs(i);
		maxl = max(maxl, t[i]);
	}

	for (int i = 1; i <= n; i++)
		if (maxl == t[i]) cout << i << " ";

}
