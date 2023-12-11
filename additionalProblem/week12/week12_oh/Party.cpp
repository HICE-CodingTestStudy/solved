
#include <iostream>
#include <queue>
#define MAX 1001

using namespace std;

int n, m, x, res;
int a, b, t;

priority_queue<pair<int, int>> pq[MAX];
int cost[MAX][MAX];

void dijkstra(int start) {

	bool visited[MAX] = { 0 };

	while (!pq[start].empty()) {
		int dist = -pq[start].top().first;
		int mid = pq[start].top().second;
		pq[start].pop();

		visited[mid] = true;

		for (int end = 1; end <= n; end++) {
			if (visited[end] || start == end) continue;

			if (dist + cost[mid][end] < cost[start][end]) {
				cost[start][end] = dist + cost[mid][end];
				pq[start].push(make_pair(-cost[start][end], end));
			}
		}
	}
}

void input() {
	cin >> n >> m >> x;

	for (int i = 0; i < m; i++) {
		cin >> a >> b >> t;
		cost[a][b] = t;
		cost[a][a] = 0;
		pq[a].push(make_pair(-t, b));
	}
}

void output() {
	for (int i = 1; i <= n; i++)
		res = max(res, cost[i][x] + cost[x][i]);

	cout << res << endl;
}

int main() {

	fill(&cost[0][0], &cost[MAX - 1][MAX - 1], 1e6);

	input();
	for (int i = 1; i <= n; i++) dijkstra(i);
	output();
}