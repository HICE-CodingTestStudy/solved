
#include <iostream>
#include <vector>
using namespace std;

vector<int> H[100], L[100];
int n, m, a, b, ans, cnt;
bool cant, visited[101];

void dfs(int cur, vector<int>* HorL) {
	visited[cur] = true;
	cnt++;

	if (cnt > (n + 1) / 2) {
		ans++;
		cant = true;
		return;
	}

	for (int i = 0; i < HorL[cur].size(); i++) {
		if (!visited[HorL[cur][i]]) dfs(HorL[cur][i], HorL);
		if (cant) return;
	}
}

void setDFS() {
	fill(visited, visited + 100, false);
	cnt = cant = 0;
}

int main() {
	cin >> n >> m;

	while (m--) {
		cin >> a >> b;
		H[a].push_back(b);  // a는 b보다 무거움
		L[b].push_back(a);  // b는 a보다 가벼움
	}

	for (int i = 1; i <= n; i++) {
		setDFS(); dfs(i, H);
		setDFS(); dfs(i, L);
	}

	cout << ans;
}
