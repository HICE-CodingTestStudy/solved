
#include <iostream>
using namespace std;

int n, w, h, r, ans;

int main() {
    cin >> n;

    if (!n) {
        cout << 0;
        return 0;
    }
    w = 1;
    while (w * w <= n) w++;

    h = n / --w;

    r = n - h * w;

    w--; h--; r--;
    while (w) {
        ans += w * h;
        if (r > 0) ans += r;
        w--; h--; r--;
    }
    cout << ans;
}