
#include <iostream>
using namespace std;

int n;
char star[500][500];

void r(int x, int y, int d) {

	if (d == 1) {
		star[x][y] = star[x + 1][y] = star[x + 2][y] = '*';
		return;
	}

	for (int i = y; i <= y + 4 * d - 4; i++)
		star[x][i] = '*';
	
	for (int i = x; i <= x + 4 * d - 2; i++)
		star[i][y] = '*';
	
	for (int i = y + 1; i <= y + 4 * d - 4; i++)
		star[x + 4 * d - 2][i] = '*';
	
	for (int i = x + 2; i <= x + 4 * d - 2; i++)
		star[i][y + 4 * d - 4] = '*';
	
	star[x + 2][y + 4 * d - 5] = '*';

	r(x + 2, y + 2, d - 1);
}

int main() {
	cin >> n;

	if (n == 1) {
		cout << '*';
		return 0;
	}

	r(1, 1, n);

	for (int i = 1; i <= 4 * n - 1; i++) {
		for (int j = 1; j <= 4 * n - 3; j++) {
			star[i][j] == '*' ? cout << '*' : cout << ' ';
			if (i == 2) break;
		}
		cout << endl;
	}
}
