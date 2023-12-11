
#include <iostream>
#include <vector>
using namespace std;

int n, t;
vector<int> v;

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> t;
		if (v.empty() || v.back() < t)
			v.push_back(t);
		else
			*lower_bound(v.begin(), v.end(), t) = t;
	}
	cout << n - v.size();
}
