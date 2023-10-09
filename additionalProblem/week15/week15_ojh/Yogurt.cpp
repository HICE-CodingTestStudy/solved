
#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#define MAX 10001
#define MAXDIST 10000000000
using namespace std;

int V, E, u, v, e, yNow, mStart, route[11];
priority_queue<pair<long long, int>> pq;
vector<pair<int, int>> edge[MAX];
vector<int> valid;

long long dijkstra(int src, int dest) {
  
	long long dist[MAX];
	fill(dist, dist + MAX, MAXDIST);

	dist[src] = 0;

	pq.push({ 0,src });

	while (!pq.empty()) {
		int cur = pq.top().second;
		int curDist = -pq.top().first;
		pq.pop();

		if (dist[cur] < curDist) continue;

		for (int i = 0; i < edge[cur].size(); i++) {
			int next = edge[cur][i].second;
			int nextDist = curDist + edge[cur][i].first;

			if (nextDist < dist[next]) {
				dist[next] = nextDist;
				pq.push({ -nextDist, next });
			}
		}
	}
	return dist[dest];
}


int main() {

	cin >> V >> E;

	for (int i = 0; i < E; i++) {
		cin >> u >> v >> e;
		edge[u].push_back({ e, v });
		edge[v].push_back({ e, u });
	}

	for (int i = 0; i < 10; i++)
		cin >> route[i];

	cin >> mStart;

	yNow = route[0];
	long long mDist = 0, yDist = 0;

  // 루트 순으로 다익스트라 실행
	for (int i = 0; i < 10; i++) {
		long long ytmpDist = dijkstra(yNow, route[i]);
		if (ytmpDist == MAXDIST) continue;
		yDist += ytmpDist;

		mDist = dijkstra(mStart, route[i]);

		if (mDist <= yDist)
			valid.push_back(route[i]);

		yNow = route[i];
	}

	if (valid.empty()) {
		cout << -1;
		return 0;
	}

	sort(valid.begin(), valid.end());

	cout << valid.front();
}
