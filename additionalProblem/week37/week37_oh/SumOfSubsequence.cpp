
#include <iostream>
using namespace std;

int n, v[21];
bool vis[2000001];

void dfs(int d, int sum) {
    vis[sum] = 1;
    if (d == n) return;
    
    dfs(d + 1, sum);
    dfs(d + 1, sum + v[d]);
}

int main() {
    cin >> n;
    
    for (int i = 0; i < n; i++)
        cin >> v[i];
    
    dfs(0, 0);
    
    for (int i = 1; i <= 2000000; i++)
        if (!vis[i]) {
            cout << i;
            return 0;
        }
}