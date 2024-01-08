
#include <iostream>
#include <algorithm>
using namespace std;

int n, l;
string s;

void dfs(int idx, string s) {
	if (idx == l - 1) {
		cout << s << '\n';
		return;
	}

	for (int i = idx; i < l; i++) {
		if (i != idx && s[i] == s[idx]) continue;
		if (s[i] != s[idx]) 
			swap(s[i], s[idx]);
		
		dfs(idx + 1, s);
	}
}

int main() {
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		cin >> s;
		l = s.size();
		sort(s.begin(), s.end());
		dfs(0, s);
	}
}
