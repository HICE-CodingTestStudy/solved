
#include <iostream>
using namespace std;

int a, b, ans, p[100001];
int main() {
    cin >> a >> b;
    
    for (int i = 2; i * 2 <= b; i++)
        if (!p[i]) {
            p[i]++;
            for (int k = i * 2; k <= b; k += i)
                p[k] = p[k / i] + 1;
        }
    for (int i = a; i <= b; i++)
        if (p[p[i]] == 1) ans++;
    
    cout << ans;
}
