
#include <iostream>

using namespace std;

int n, cnt, tmp, y[50003];

int main() {
	cin >> n;

	for (int i = 1; i <= n; i++)
		cin >> tmp >> y[i];
	
	for (int i = 1; i <= n; i++)
		for (int j = i + 1; j <= n + 1; j++)
			if (y[i] == y[j])
				y[j] = 0;
			else if (y[i] > y[j]) {
				cnt++;
				break;
			}

	cout << cnt;
}