
#include <iostream>
#include <vector>
using namespace std;

int t, n;

int distance(string a, string b) {
	int d = 0;
	for (int i = 0; i < 4; i++)
		if (a[i] != b[i]) d++;
	return d;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> t;
	while (t--) {
		vector<string> s;

		cin >> n;
		for (int j = 0; j < n; j++) {
			string str;
			cin >> str;
			s.push_back(str);
		}
		if (n > 32)
			cout << 0 << '\n';
		else {
			int ans = 1e9;
			for (int i = 0; i < n - 2; i++) {
				for (int j = i + 1; j < n - 1; j++) {
					for (int k = j + 1; k < n; k++) {
						ans = min(ans, distance(s[i], s[j]) + distance(s[j], s[k]) + distance(s[i], s[k]));
					}
				}
			}
			cout << ans << '\n';
		}
	}
}