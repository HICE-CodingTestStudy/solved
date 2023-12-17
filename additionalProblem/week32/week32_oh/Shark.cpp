
#include <iostream>
using namespace std;

struct Fish {
	int x, y, d;
	bool dead;
};

Fish fish[16];
int ans, map[4][4];
int dx[] = { -1,-1,0,1,1,1,0,-1 };
int dy[] = { 0,-1,-1,-1,0,1,1,1 };

bool checkBoundary(int x, int y) {
	return x >= 0 && y >= 0 && x < 4 && y < 4;
}

void moveFish() {
	for (int i = 0; i < 16; i++) {
		bool move = false;
		int cnt = 0;
		int x = fish[i].x, y = fish[i].y;
		if (map[x][y] < 0 || fish[i].dead) continue;

		while (!move && cnt <= 8) {
			cnt++;
			if (move) break;

			int nx = x + dx[fish[i].d], ny = y + dy[fish[i].d];

			if (checkBoundary(nx, ny) && map[x][y] >= 0 && map[nx][ny] != -2) {
				if (map[nx][ny] >= 0) {
					move = true;
					swap(fish[map[nx][ny]].x, fish[i].x);
					swap(fish[map[nx][ny]].y, fish[i].y);
					swap(map[x][y], map[nx][ny]);
					break;
				}
				else if (map[nx][ny] == -1) {
					move = true;
					fish[i].x = nx, fish[i].y = ny;
					swap(map[x][y], map[nx][ny]);
					break;
				}
			} else {
				fish[i].d = (fish[i].d + 1) % 8;
			}
		}
	}
}

void copyFish(int fromMap[][4], int toMap[][4], Fish fromFish[], Fish toFish[]) {
	for (int i = 0; i < 4; i++)
		for (int j = 0; j < 4; j++) {
			toMap[i][j] = fromMap[i][j];
			toFish[i * 4 + j] = fromFish[i * 4 + j];
		}
}

void dfs(int nx, int ny, int ssize, int fd) {
	ans = max(ans, ssize);

	Fish tmpFish[16];
	int tmpMap[4][4];
	copyFish(map, tmpMap, fish, tmpFish);
	moveFish();

	int nextX = nx, nextY = ny;
	for (int i = 0; i < 3; i++) {
		nextX += dx[fd], nextY += dy[fd];
		if (!checkBoundary(nextX, nextY)) break;
		
		if (map[nextX][nextY] != -1) {
			int tmpSize = map[nextX][nextY];
			int tmpDir = fish[tmpSize].d;

			map[nx][ny] = -1;
			map[nextX][nextY] = -2;
			fish[tmpSize].dead = true;

			dfs(nextX, nextY, ssize + tmpSize + 1, tmpDir);

			fish[tmpSize].dead = false;
			map[nextX][nextY] = tmpSize;
			map[nx][ny] = -2;
		}
	}

	copyFish(tmpMap, map, tmpFish, fish);
}

int main() {
	for (int i = 0; i < 4; i++)
		for (int j = 0; j < 4; j++) {
			int dir;
			cin >> map[i][j];
			map[i][j]--;
			cin >> dir;
			Fish tmpFish{ i, j, dir - 1, false };
			fish[map[i][j]] = tmpFish;
		}

	int tmpSize = map[0][0];
	int tmpDir = fish[map[0][0]].d;
	fish[map[0][0]].dead = true;
	map[0][0] = -2;
	dfs(0, 0, tmpSize + 1, tmpDir);

	cout << ans;
}
