
#include <iostream>
using namespace std;

int r, c, q, X1, Y1, X2, Y2, p[1001][1001];

int main() {
	cin >> r >> c >> q;

	for (int i = 1; i <= r; i++)
		for (int j = 1; j <= c; j++) {
			cin >> p[i][j];
			p[i][j] += p[i][j - 1] + p[i - 1][j] - p[i - 1][j - 1];
		}
	
	while (q--) {
		cin >> X1 >> Y1 >> X2 >> Y2;

		cout << (p[X2][Y2] - p[X1 - 1][Y2] - p[X2][Y1 - 1] + p[X1 - 1][Y1 - 1]) / ((X2 - X1 + 1) * (Y2 - Y1 + 1)) << endl;
	}
}