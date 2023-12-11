
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

string ary[5];
int ans, visited[25];
int dx[] = { -1,1,0,0 }, dy[] = { 0,0,-1,1 };
struct Point { int x, y; };
Point p;
vector<Point> v;

bool isNear() {
	queue<Point> q;
	int flag[7] = { 1 };
	q.push(v[0]);

	while (!q.empty()) {
		int x = q.front().x, y = q.front().y;
		q.pop();
		for (int i = 0; i < 7; i++) {
			if (flag[i]) continue;
			for (int j = 0; j < 4; j++)
				if (v[i].x == x + dx[j] && v[i].y == y + dy[j]) {
					flag[i] = 1;
					q.push(v[i]);
				}
		}
	}

	for (int i = 0; i < 7; i++)
		if (!flag[i]) return false;
	
	return true;
}

void dfs(int idx, int lee, int lim) {
	if (lee + lim == 7) {
		if (lim > lee) return;
		if (isNear())
			ans++;
		return;
	}

	for (int i = idx; i < 25; i++) {
		if (visited[i]) continue;
		int x = i / 5, y = i % 5;
		p.x = x; p.y = y;
        
		v.push_back(p);
		visited[i] = 1;
		if (ary[x][y] == 'Y')
			dfs(i, lee, lim + 1);
		else
			dfs(i, lee + 1, lim);
		visited[i] = 0;
		v.pop_back();
	}
}

int main() {
	string s;
	for (int i = 0; i < 5; i++)
		cin >> ary[i];
	
	dfs(0, 0, 0);
	cout << ans;
}
