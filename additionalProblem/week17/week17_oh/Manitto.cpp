
#include <iostream>
#include <map>
using namespace std;

int n, ans, num = 1;
map<string, string> m;
string a, b, x, tp, prev;

int main() {
	
	while (1) {
		cin >> n;
		if (!n) break;

		for (int i = 0; i < n; i++) {
			cin >> a >> b;
			m[a] = b;
		}

		ans = 0;
		tp = m.begin()->first;
		x = tp;
    
		while (!m.empty()) {
			prev = x;
			x = m[x];
			m.erase(prev);

			if (tp == x) {
				ans++;
				if (!m.empty()) tp = m.begin()->first;
				x = tp;
			}
		}

		cout << num << " " << ans << endl;
		num++;
	}
}
