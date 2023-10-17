
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int T, n, m, t, s, g, h, a, b, d, pos[100], dist[2001];
priority_queue<pair<int, int>> pq;
priority_queue<int> res;

void dijkstra(int start, vector<pair<int, int>> graph[2001]) {
	fill(dist, dist + 2001, 1e9);
	dist[start] = 0;

	pq.push({ 0, start });

	while (!pq.empty()) {
		int cur = pq.top().second;
		int curDist = -pq.top().first;
		pq.pop();

		if (curDist > dist[cur]) continue;

		for (int i = 0; i < graph[cur].size(); i++) {
			int next = graph[cur][i].second;
			int nextDist = curDist + graph[cur][i].first;

			if (nextDist < dist[next]) {
				dist[next] = nextDist;
				pq.push({ -nextDist, next });
			}
		}
	}
}

int main() {
	cin >> T;

	while (T--) {

		vector<pair<int, int>> graph[2001];

		cin >> n >> m >> t >> s >> g >> h;
		if (g > h) swap(g, h);

		int must = 1e8;
		for (int i = 0; i < m; i++) {
			cin >> a >> b >> d;
			if (a == g && b == h) must = d;
			graph[a].push_back({ d,b });
			graph[b].push_back({ d,a });
		}

		for (int i = 0; i < t; i++)
			cin >> pos[i];

		for (int i = 0; i < t; i++) {
			int A1, A2, B1, B2, C;
			dijkstra(s, graph);
			A1 = dist[g]; B1 = dist[h]; C = dist[pos[i]];
			dijkstra(h, graph); A2 = dist[pos[i]];
			dijkstra(g, graph); B2 = dist[pos[i]];
			
			if (C == A1 + must + A2 || C == B1 + must + B2)
                res.push(-pos[i]);
		}

		while (!res.empty()) {
			int ans = -res.top();
			res.pop();
			cout << ans << " ";
		}
		cout << '\n';
	}
}