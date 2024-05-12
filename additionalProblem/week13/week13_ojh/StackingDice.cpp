
#include <iostream>

using namespace std;

int n, tmp, ans, dice[10001][6];

int MaxReturn(int i, int a) {
	int tmpMax = 0;

	if (a == 0 || a == 5) {
		tmpMax = max(tmpMax, dice[i][1]); tmpMax = max(tmpMax, dice[i][2]);
		tmpMax = max(tmpMax, dice[i][3]); tmpMax = max(tmpMax, dice[i][4]);
	}
	else if (a == 1 || a == 3) {
		tmpMax = max(tmpMax, dice[i][0]); tmpMax = max(tmpMax, dice[i][2]);
		tmpMax = max(tmpMax, dice[i][4]); tmpMax = max(tmpMax, dice[i][5]);
	}
	else {
		tmpMax = max(tmpMax, dice[i][0]); tmpMax = max(tmpMax, dice[i][1]);
		tmpMax = max(tmpMax, dice[i][3]); tmpMax = max(tmpMax, dice[i][5]);
	}

	return tmpMax;
}

int switchIndex(int i) {
	switch (i) {
	case 0:
		return 5;
	case 1:
		return 3;
	case 2:
		return 4;
	case 3:
		return 1;
	case 4:
		return 2;
	case 5:
		return 0;
	}
}

int main() {
	cin >> n;

	// A B C D E F	// (A,F) (B,D) (C,E) 05, 13, 24
	for (int i = 1; i <= n; i++)
		for (int j = 0; j < 6; j++)
			cin >> dice[i][j];
	
	for (int i = 0; i < 6; i++) {
		int top = dice[1][i];
		tmp = 0;
		for (int j = 1; j <= n; j++) {
			int index;
			for (int k = 0; k < 6; k++) {
				if (top == dice[j][k])
					index = k;
			}
			tmp += MaxReturn(j, index);

			top = dice[j][switchIndex(index)];
		}

		ans = max(ans, tmp);
	}

	cout << ans;
}
