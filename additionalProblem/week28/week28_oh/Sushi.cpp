
#include <iostream>
using namespace std;

int n, d, k, c, num, ans, a, ary[30001], cnt[3001];

int main() {
	cin >> n >> d >> k >> c;
	cnt[c]++;
	a++;

	for (int i = 0; i < n; i++)
		cin >> ary[i];
  
	for (int i = 0; i < k; i++)
   		 a += !cnt[ary[i]]++;
  
	ans = a;
  
	for (int i = 1; i < n; i++) {
		a -= !(--cnt[ary[i - 1]]);
		a += !cnt[ary[(i + k - 1) % n]]++;
		ans = max(ans, a);
	}
	cout << ans;
}
