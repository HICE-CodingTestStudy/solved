
#include <iostream>
#include <vector>
using namespace std;

int r, c;
vector<string> v;
string s;
int resV, resK, subV, subK;
bool visited[250][250];

// 상하좌우
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

bool checkBoundary(int i, int j) {

	if (i <= 0 || j <= 0 || i >= r || j >= c)
		return false;
	else return true;
}

void dfs(int row, int col) {

	visited[row][col] = true;

	if (v[row][col] == 'v')
		subV++;
	else if (v[row][col] == 'k')
		subK++;

	for (int i = 0; i < 4; i++) {
		int nextR = row + dx[i];
		int nextC = col + dy[i];
		if (checkBoundary(nextR, nextC) && !visited[nextR][nextC] && v[nextR][nextC] != '#')
			dfs(nextR, nextC);
	}
}

int main() {
	cin >> r >> c;

	for (int i = 0; i < r; i++) {
		cin >> s;
		v.push_back(s);
	}
	
	for (int i = 0; i < r; i++)
		for (int j = 0; j < c; j++)
			if (!visited[i][j] && v[i][j] != '#') {
				dfs(i, j);

				subV >= subK ? subK = 0 : subV = 0;
				
				resV += subV; resK += subK;

				subV = subK = 0;
			}
		
	cout << resK << ' ' << resV;
}
