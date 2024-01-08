
#include <iostream>
using namespace std;

int n, m, c, a, p[502][502];

int dfs(int x, int y) {
    if (!p[x][y]) return 0;
    p[x][y] = 0;
    return dfs(x, y - 1) + dfs(x, y + 1) + dfs(x - 1, y) + dfs(x + 1, y) + 1;
}

int main() {
    cin >> n >> m;
    
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= m; j++)
            cin >> p[i][j];
    
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= m; j++)
            if (p[i][j]) {
                c++;
                a = max(a, dfs(i, j));
            }
    cout << c << endl << a;
}
