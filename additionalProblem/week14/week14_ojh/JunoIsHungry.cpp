#include <iostream>
using namespace std;

int n, x[50001], v[50001];
double t, l, r = 1e9;

int main() {

	cin >> n >> t;

	for (int i = 0; i < n; i++)
		cin >> x[i];

	for (int i = 0; i < n; i++)
		cin >> v[i];

	int flag = 1;

	l = (double)x[0] - t * v[0];
	r = (double)x[0] + t * v[0];

	for (int i = 1; i < n; i++) {
		double nextL = (double)x[i] - t * v[i];
		double nextR = (double)x[i] + t * v[i];
		
		if (l > nextR + 1e-08 || r < nextL - 1e-08) {
			flag = 0;
			break;
		}

		l = max(l, nextL);
		r = min(r, nextR);
	}

	cout << flag;
}
