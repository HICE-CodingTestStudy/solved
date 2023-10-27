
#include <iostream>
#include <vector>
using namespace std;

int c, p, ans, H[101];
vector<vector<int>> b[7] = {
	{{0}, {0, 0, 0, 0}},
	{{0, 0}},
	{{0, 0, 1}, {0, -1}},
	{{0, -1, -1}, {0, 1}},
	{{0, 0, 0}, {0, -1, 0}, {0, 1}, {0, -1}},
	{{0, 0, 0}, {0, 1, 1}, {0, 0}, {0, -2}},
	{{0, 0, 0}, {0, 0, -1}, {0, 0}, {0, 2}}
};

int main() {
	cin >> c >> p;
    
	for (int i = 0; i < c; i++)
		cin >> H[i];

	for (auto x : b[p - 1])
		for (int i = 0; i <= c - x.size(); i++) {
			bool ok = 1;
			for (int j = 0; j < x.size(); j++)
				if (H[i + j] != H[i] + x[j]) ok = false;
			if (ok) ans++;
		}
    
	cout << ans;
}
