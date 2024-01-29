
#include <iostream>
using namespace std;

int n, m, cnt;
bool b[27][27];

void sol(int r, int c) {
	if (r == n) {
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < m - 1; j++)
				if (b[i][j] && b[i + 1][j] && b[i][j + 1] && b[i + 1][j + 1])
					return;

		cnt++;
		return;
	}

	int nc = (c + 1) % m;
	int nr = (!nc) ? r + 1 : r;

	b[r][c] = 1;
	sol(nr, nc);

	b[r][c] = 0;
	sol(nr, nc);
}

int main() {
	cin >> n >> m;
	sol(0, 0);
	cout << cnt;
}