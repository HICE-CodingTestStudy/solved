
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

char board[11][11];
int n, m, rx, ry, bx, by, ox, oy;
queue<int> cntq;
queue<pair<int, int>> rq;
queue<pair<int, int>> bq;

// RB 같은 라인에 있는 경우에 회전할 때 해당 방향 처리

void push_queue(int rx, int ry, int bx, int by, int cnt) {
	cntq.push(cnt);
	rq.push(make_pair(rx, ry));
	bq.push(make_pair(bx, by));
}

void left(int rx, int ry, int bx, int by, int cnt) {	// 더 왼쪽에 있는 것 먼저 처리
	int rcheck = 0, bcheck = 0;

	if (ry < by) {	// R이 B보다 왼쪽
		while (board[rx][ry - 1] != '#') {
			ry--;
			if (rx == ox && ry == oy) {
				rcheck = 1;
				break;
			}
		}
		if (!rcheck) board[rx][ry] = 'R';
		while (board[bx][by - 1] != '#' && board[bx][by - 1] != 'R') {
			by--;
			if (bx == ox && by == oy) {
				bcheck = 1;
				break;
			}
		}
	}
	else {		// B가 R보다 왼쪽
		while (board[bx][by - 1] != '#') {
			by--;
			if (bx == ox && by == oy) {
				bcheck = 1;
				break;
			}
		}
		if (!bcheck) board[bx][by] = 'B';
		while (board[rx][ry - 1] != '#' && board[rx][ry - 1] != 'B') {
			ry--;
			if (rx == ox && ry == oy) {
				rcheck = 1;
				break;
			}
		}
	}

	if (cnt <= 10 && rcheck && !bcheck) {
		cout << cnt;
		exit(0);
	}
	if (!rcheck && !bcheck) {
		board[rx][ry] = board[bx][by] = '.';

		push_queue(rx, ry, bx, by, cnt);
	}
}

void right(int rx, int ry, int bx, int by, int cnt) {	// 더 오른쪽에 있는 것 먼저 처리

	int rcheck = 0, bcheck = 0;

	if (ry > by) {	// R이 B보다 오른쪽
		while (board[rx][ry + 1] != '#') {
			ry++;
			if (rx == ox && ry == oy) {
				rcheck = 1;
				break;
			}
		}
		if (!rcheck) board[rx][ry] = 'R';
		while (board[bx][by + 1] != '#' && board[bx][by + 1] != 'R') {
			by++;
			if (bx == ox && by == oy) {
				bcheck = 1;
				break;
			}
		}
	}
	else {		// B가 R보다 오른쪽
		while (board[bx][by + 1] != '#') {
			by++;
			if (bx == ox && by == oy) {
				bcheck = 1;
				break;
			}
		}
		if (!bcheck) board[bx][by] = 'B';
		while (board[rx][ry + 1] != '#' && board[rx][ry + 1] != 'B') {
			ry++;
			if (rx == ox && ry == oy) {
				rcheck = 1;
				break;
			}
		}
	}

	if (cnt <= 10 && rcheck && !bcheck) {
		cout << cnt;
		exit(0);
	}

	if (!rcheck && !bcheck) {
		board[rx][ry] = board[bx][by] = '.';

		push_queue(rx, ry, bx, by, cnt);
	}
}

void up(int rx, int ry, int bx, int by, int cnt) {	// 더 위에 있는 것 먼저 처리

	int rcheck = 0, bcheck = 0;

	if (rx < bx) {	// R이 B보다 위쪽
		while (board[rx - 1][ry] != '#') {
			rx--;
			if (rx == ox && ry == oy) {
				rcheck = 1;
				break;
			}
		}
		if (!rcheck) board[rx][ry] = 'R';
		while (board[bx - 1][by] != '#' && board[bx - 1][by] != 'R') {
			bx--;
			if (bx == ox && by == oy) {
				bcheck = 1;
				break;
			}
		}
	}
	else {		// B가 R보다 위쪽
		while (board[bx - 1][by] != '#') {
			bx--;
			if (bx == ox && by == oy) {
				bcheck = 1;
				break;
			}
		}
		if (!bcheck) board[bx][by] = 'B';
		while (board[rx - 1][ry] != '#' && board[rx - 1][ry] != 'B') {
			rx--;
			if (rx == ox && ry == oy) {
				rcheck = 1;
				break;
			}
		}
	}

	if (cnt <= 10 && rcheck && !bcheck) {
		cout << cnt;
		exit(0);
	}

	if (!rcheck && !bcheck) {
		board[rx][ry] = board[bx][by] = '.';

		push_queue(rx, ry, bx, by, cnt);
	}
}

void down(int rx, int ry, int bx, int by, int cnt) {	// 더 아래에 있는 것 먼저 처리

	int rcheck = 0, bcheck = 0;

	if (rx > bx) {	// R이 B보다 아래쪽
		while (board[rx + 1][ry] != '#') {
			rx++;
			if (rx == ox && ry == oy) {
				rcheck = 1;
				break;
			}
		}
		if (!rcheck) board[rx][ry] = 'R';
		while (board[bx + 1][by] != '#' && board[bx + 1][by] != 'R') {
			bx++;
			if (bx == ox && by == oy) {
				bcheck = 1;
				break;
			}
		}
	}
	else {		// B가 R보다 아래쪽
		while (board[bx + 1][by] != '#') {
			bx++;
			if (bx == ox && by == oy) {
				bcheck = 1;
				break;
			}
		}
		if (!bcheck) board[bx][by] = 'B';
		while (board[rx + 1][ry] != '#' && board[rx + 1][ry] != 'B') {
			rx++;
			if (rx == ox && ry == oy) {
				rcheck = 1;
				break;
			}
		}
	}

	if (cnt <= 10 && rcheck && !bcheck) {
		cout << cnt;
		exit(0);
	}

	if (!rcheck && !bcheck) {
		board[rx][ry] = board[bx][by] = '.';

		push_queue(rx, ry, bx, by, cnt);
	}
}

void bfs() {

	int cnt = cntq.front();
	rx = rq.front().first; ry = rq.front().second;
	bx = bq.front().first; by = bq.front().second;

	cntq.pop(); rq.pop(); bq.pop();

	if (cnt > 10) {
		cout << -1;
		exit(0);
	}

	
	cnt++;

	left(rx, ry, bx, by, cnt);
	right(rx, ry, bx, by, cnt);
	up(rx, ry, bx, by, cnt);
	down(rx, ry, bx, by, cnt);
}


int main() {

	cin >> n >> m;

	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= m; j++) {
			cin >> board[i][j];

			switch (board[i][j]) {
			case 'R':
				rx = i;
				ry = j;
				board[i][j] = '.';
				break;
			case 'B':
				bx = i;
				by = j;
				board[i][j] = '.';
				break;
			case 'O':
				ox = i;
				oy = j;
				break;
			}
		}

	cntq.push(0);
	rq.push(make_pair(rx, ry));
	bq.push(make_pair(bx, by));

	while (!cntq.empty()) {

		bfs();
	}
}
