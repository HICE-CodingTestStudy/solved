
#include <iostream>
#include <vector>
#include <stack>
#include <set>
using namespace std;

string str;
stack<int> s;
vector<pair<int, int>> v;
set<string> ans;
bool visited[201], ch[201];

void sol(int c, int idx) {
	if (c) {
		string tmp = "";
		for (int i = 0; i < str.size(); i++)
			if (!ch[i]) tmp += str[i];
		ans.insert(tmp);
	}

	for (int i = idx; i < v.size() && !visited[i]; i++) {
		visited[i] = ch[v[i].first] = ch[v[i].second] = true;
		sol(c + 1, idx + 1);
		visited[i] = ch[v[i].first] = ch[v[i].second] = false;
	}
}

int main() {
	cin >> str;
	for (int i = 0; i < str.size(); i++)
		if (str[i] == '(')
			s.push(i);
		else if (str[i] == ')') {
			v.push_back({ s.top(), i });
			s.pop();
		}

	sol(0, 0);
	for (auto a : ans)
		cout << a << '\n';
}
