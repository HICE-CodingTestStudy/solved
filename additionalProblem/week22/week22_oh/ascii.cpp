
#include <iostream>
using namespace std;

int h, w, W, flag;
char c;
double ans;

int main() {
    cin >> h >> w;
    while (h--) {
        W = w;
        while (W--) {
            cin >> c;
            if (c == '.') ans = flag ? ans + 1 : ans;
            else ans += 0.5, flag = !flag;
        }
    }
    cout << ans;
}
