
#include <iostream>
#include <algorithm>
using namespace std;

struct planet {
	int psize, index;
	planet(int ps, int i) :psize(ps), index(i) {}
	planet() {}
	bool operator<(const planet& p) { return psize < p.psize; }
};

int m, n, res;
planet u[100][10001];

bool equal(int i, int j, int k) {
	if (u[i][k].psize == u[i][k - 1].psize &&
		u[j][k].psize != u[j][k - 1].psize) return false;
	if (u[i][k].psize != u[i][k - 1].psize &&
		u[j][k].psize == u[j][k - 1].psize) return false;
	
	return true;
}

int main() {

	cin >> m >> n;

	for (int i = 0, x; i < m; i++)
		for (int j = 0; j < n; j++) {
			cin >> x;
			u[i][j] = planet(x, j);
		}
	
	for (int i = 0; i < m; i++)
		sort(u[i], u[i] + n);

	for (int i = 0; i < m; i++)
		for (int j = i + 1; j < m; j++) {
			int k = 0;
			for (; k < n; k++)
				if (k && !equal(i, j, k) || u[i][k].index != u[j][k].index) break;
			if (k == n) res++;
		}
	cout << res;
}
