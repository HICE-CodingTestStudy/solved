
#include <iostream>
#include <vector>
using namespace std;

string s;
int l, r, ans, lk[3000001], rk[3000001];
vector<int> ridx;

int main() {
	cin >> s;

	int lc = 0, rc = 0;
	for (int i = 0; i < s.size(); i++) {
		if (s[i] == 'K')
			lc++;
		else {
			lk[i] = lc;
			ridx.push_back(i);
		}

		if (s[s.size() - 1 - i] == 'K')
			rc++;
		else 
			rk[s.size() - 1 - i] = rc;
	}

	l = 0;
	r = ridx.size() - 1;
	while (l <= r) {
		int lks = lk[ridx[l]], rks = rk[ridx[r]];
		ans = max(ans, r - l + 1 + min(lks, rks) * 2);

		if (lks > rks) r--;
		else l++;
	}

	cout << ans;
}
