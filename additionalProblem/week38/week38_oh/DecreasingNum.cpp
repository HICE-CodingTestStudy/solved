
#include <iostream>
#include <vector>
#include <algorithm>
#define ll long long
using namespace std;

int n, idx = 1, k;
vector<ll> v;

void dfs(ll x, int d) {
    if (d > 9) return;

    v.push_back(x);

    for (int i = 0; i < x % 10; i++)
        dfs(x * 10 + i, d + 1);
}

int main() {
    cin >> n;

    for (int i = 0; i <= 9; i++)
        dfs(i, 0);

    sort(v.begin(), v.end());

    n > v.size() ? cout << -1 : cout << v[n - 1];
}