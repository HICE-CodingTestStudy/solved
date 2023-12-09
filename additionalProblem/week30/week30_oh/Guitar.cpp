
#include <iostream>
using namespace std;

int n, m, l, r, ary[100001];

int main() {
	cin >> n >> m;
  
	for (int i = 0; i < n; i++) {
		cin >> ary[i];
    l = max(l, ary[i]);
		r += ary[i];
	}

	while (l <= r) {
		int blen = (l + r) / 2;

		int sum = 0, cnt = 0;
		for (int i = 0; i < n; i++) {
			if (sum + ary[i] > blen) {
				sum = 0;
				cnt++;
			}
			sum += ary[i];
		}
		if (sum) cnt++;	
		
		if (cnt > m) l = blen + 1;
		else r = blen - 1;
	}
	cout << l;
}
