#include <iostream>
using namespace std;

int n, d, x, y, minX, minY, maxX, maxY;
int dx[] = { 1,0,-1,0 }, dy[] = { 0,1,0,-1 };
char c, map[101][101];

int main() {
	fill(map[0], map[101], '#');
	map[50][50] = '.';

	x = y = minX = minY = maxX = maxY = 50;
    
	cin >> n;
	while (n--) {
		cin >> c;
		if (c == 'R') d = (d + 3) % 4;
		else if (c == 'L') d = (d + 1) % 4;
		else {
			x += dx[d];
			y += dy[d];
			minX = min(minX, x);
			minY = min(minY, y);
			maxX = max(maxX, x);
			maxY = max(maxY, y);
			map[x][y] = '.';
		}
}

	for (int i = minX; i <= maxX; i++) {
		for (int j = minY; j <= maxY; j++)
			cout << map[i][j];
		cout << endl;
	}
}
