
#include <iostream>

using namespace std;

int n, coin[101][2];

int main() {
	
	for (int i = 0; i < 3; i++) {
		cin >> n;
		int total = 0, dp[50001] = { 1 };

		for (int j = 1; j <= n; j++) {
			cin >> coin[j][0] >> coin[j][1];
			total += coin[j][0] * coin[j][1];
		}

		int target = total / 2;

		if (total % 2) {
			cout << '0' << endl;
			continue;
		}
		
		for (int j = 1; j <= n; j++) {
			int amount = coin[j][0], cnt = coin[j][1];

			for (int k = target; k >= 0; k--)
				if (k - amount >= 0 && dp[k - amount])
					for (int c = 0; c < cnt; c++)
						if (k + amount * c <= target)
							dp[k + amount * c] = 1;
		}

		cout << dp[target] << endl;
	}
}
