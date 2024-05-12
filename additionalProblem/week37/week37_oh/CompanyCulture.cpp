
#include <iostream>
#include <vector>
using namespace std;

int n, m, s, a, b;
vector<vector<int>> v;
vector<int> p;

void dfs(int cur) {
    for (int i = 0; i < v[cur].size(); i++) {
        p[v[cur][i]] += p[cur];
        dfs(v[cur][i]);
    }
}

int main() {
    cin >> n >> m;
    v.assign(n + 1, vector<int>(0, 0));
    p.assign(n + 1, 0);

    for (int i = 1; i <= n; i++) {
        cin >> s;
        if (s != -1) 
            v[s].push_back(i);
    }

    for (int i = 0; i < m; i++) {
        cin >> a >> b;
        p[a] += b;
    }

    dfs(1);

    for (int i = 1; i <= n; i++)
        cout << p[i] << " ";
}