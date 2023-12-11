
#include <iostream>
using namespace std;

int n, k, pCount, res, seq[103], plug[103];

int main() {
	cin >> n >> k;

	for (int i = 1; i <= k; i++)
		cin >> seq[i];

	for (int i = 1; i <= k; i++) {

		if (pCount < n) {
			if (plug[seq[i]] || seq[i - 1] == seq[i]) continue;
			plug[seq[i]] = 1;
			pCount++;
		}
		else if (!plug[seq[i]]) {
			int outNum, tmp = 0;

			for (int j = 1; j <= k; j++)
				if (plug[j]) {
					int x = i + 1;
					for (; x <= k + 1; x++)
						if (seq[x] == j) break;

					if (tmp < x) {
						tmp = x;
						outNum = j;
					}
				}
			plug[outNum] = 0;
			plug[seq[i]] = 1;

			res++;
		}
	}
	cout << res;
}