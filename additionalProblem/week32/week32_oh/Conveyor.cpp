
#include <iostream>
#include <deque>
using namespace std;

int n, k, x, ans;
deque<int> robot;
deque<int> dq;

int getZeroNum() {
	int ret = 0;
	for (int i = 0; i < dq.size(); i++)
		if (!dq[i]) ret++;
	return ret;
}

void sol() {
	while (1) {
		if (getZeroNum() >= k) break;
		ans++;

		dq.push_front(dq.back());
		dq.pop_back();
		robot.push_front(robot.back());
		robot.pop_back();
		robot[n - 1] = 0;

		for (int i = n - 2; i >= 0; i--)
			if (robot[i] && !robot[i + 1] && dq[i + 1]) {
				dq[i + 1]--;
				robot[i] = 0;
				if (i == n - 2) continue;
				robot[i + 1] = 1;
			}

		if (dq[0] && !robot[0]) {
			robot[0] = 1;
			dq[0]--;
		}
	}
}

int main() {
	cin >> n >> k;

	for (int i = 0; i < 2 * n; i++) {
		cin >> x;
		dq.push_back(x);
		robot.push_back(0);
	}

	sol();
	cout << ans;
}
