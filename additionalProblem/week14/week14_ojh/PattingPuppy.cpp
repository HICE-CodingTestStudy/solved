
#include <iostream>

using namespace std;

int x, y, cnt;

int main() {
	cin >> x >> y;

	int diff = y - x;

	for (int i = 1; i <= y / 2; i++) {

		int nextDiff = diff - 2 * i;

		if (nextDiff >= 0) cnt += 2;

		if (!nextDiff) break;
		// 1 ~ i+1 까지
		else if (nextDiff > 0 && nextDiff <= i + 1) {
			cnt++;
			break;
		}
		// i+1 ~ 2*(i+1) 까지
		else if (nextDiff > i + 1 && nextDiff <= 2 * (i + 1)) {
			cnt += 2;
			break;
		}
		diff = nextDiff;
	}

	y - x == 1 ? cout << 1 : cout << cnt;
}