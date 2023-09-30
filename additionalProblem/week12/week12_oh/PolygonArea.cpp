#include <iostream>

using namespace std;

long long n, res, xy[2][10005];

int main() {
	scanf("%ld", &n);

	for (int i = 0; i < n; i++)
		scanf("%ld %ld", &xy[0][i], &xy[1][i]);
	
	xy[0][n] = xy[0][0];
	xy[1][n] = xy[1][0];

	for (int i = 0; i < n; i++)
		res += xy[0][i] * xy[1][i + 1] - xy[0][i + 1] * xy[1][i];
	
	res = res < 0 ? -res : res;

	printf("%.1f", (double)res/2);
}
