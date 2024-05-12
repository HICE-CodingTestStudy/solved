
#include <iostream>
using namespace std;

int n, w, u, v, num, c[500001];

int main() {
    cin >> n >> w;
    
    for (int i = 0; i < n - 1; i++) {
        scanf("%d%d", &u, &v);
        c[u]++;
        c[v]++;
    }
    
    for (int i = 2; i <= n; i++)
        num += c[i] == 1;
    
    cout.precision(6);
    cout << fixed << w / (double)num;
}
