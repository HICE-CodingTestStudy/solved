
#include <iostream>
#include <algorithm>
using namespace std;

struct planet {
	int psize, index;
	planet(int ps, int i) :psize(ps), index(i) {}
	planet() {}
	bool operator<(const planet& p) { return psize < p.psize;}
};

int m, n, cnt, res;
planet universe[100][10001];

bool checkDiff(int i, int j, int k) {
	if (k && universe[i][k].psize == universe[i][k - 1].psize &&
		universe[j][k].psize != universe[j][k - 1].psize) return true;
	if (k && universe[i][k].psize != universe[i][k - 1].psize &&
		universe[j][k].psize == universe[j][k - 1].psize) return true;
	
	return false;
}

int main() {
	
	cin >> m >> n;

	int x;
	for (int i = 0; i < m; i++)
		for (int j = 0; j < n; j++) {
			cin >> x;
			universe[i][j] = planet(x, j);
		}
	
	for (int i = 0; i < m; i++)
		sort(universe[i], universe[i] + n);

	for (int i = 0; i < m; i++) {
		cnt = 1;
		for (int j = i + 1; j < m; j++) {
			if (universe[j][0].index < 0) continue;
			int k = 0;
			for (; k < n; k++) 
				if (checkDiff(i, j, k) || universe[i][k].index != universe[j][k].index) break;
			if (k == n) {
				universe[j][0].index = -1;
				cnt++;
			}
		}

		res += cnt * (cnt - 1) / 2;
	}

	cout << res;
}
