
#include <iostream>
#include <vector>
using namespace std;
struct F {
	int p, f, s, v, c;
};
F a[15];
int N, mp, mf, ms, mv, ans = 1e9;
vector<int> vec, ansv;

void solve(int n, int p, int f, int s, int v, int c) {
	if (n == N) {
		if (p >= mp && f >= mf && s >= ms && v >= mv) {
			if (ans > c) {
				ansv = vec;
				ans = c;
			}
			else if (ans == c && ansv > vec)
				ansv = vec;
		}
		return;
	}

	solve(n + 1, p, f, s, v, c);
	vec.push_back(n);
	solve(n + 1, p + a[n].p, f + a[n].f, s + a[n].s, v + a[n].v, c + a[n].c);
	vec.pop_back();
}

int main() {
	cin >> N >> mp >> mf >> ms >> mv;

	for (int i = 0; i < N; i++)
		cin >> a[i].p >> a[i].f >> a[i].s >> a[i].v >> a[i].c;

	solve(0, 0, 0, 0, 0, 0);
    
	if (ans == 1e9) {
		cout << -1;
		return 0;
	}
    
	cout << ans << endl;
	for (auto k : ansv)
        cout << k + 1 << " ";
}
