
#include <iostream>
using namespace std;

int t, n, m, a, b, c, k, f[101], w[101][101];

int main() {
    cin >> t;
    
    while (t--) {
        cin >> n >> m;
        
        fill(w[0], w[101], 1e9);
        
        for (int i = 0; i <= n; i++) w[i][i] = 0;
        
        for (int i = 0; i < m; i++) {
            cin >> a >> b >> c;
            w[a][b] = c;
            w[b][a] = c;
        }
        
        cin >> k;
        for (int i = 0; i < k; i++)
            cin >> f[i];
        
        for (int k = 1; k <= n; k++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    if (w[i][k] + w[k][j] < w[i][j])
                        w[i][j] = w[i][k] + w[k][j];
        
        int rnum, sum = 1e9;
        for (int i = 1; i <= n; i++) {
            int sub = 0;
            for (int j = 0; j < k; j++)
                sub += w[f[j]][i];
            if (sum > sub) {
                sum = sub;
                rnum = i;
            }
        }
        cout << rnum << endl;
    }
}
