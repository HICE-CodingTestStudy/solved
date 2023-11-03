
#include <iostream>
using namespace std;

int h, w, flag;
char c;
double ans;

int main() {
    cin >> h >> w;
    
    for (int i = 0; i < h; i++)
        for (int j = 0; j < w; j++) {
            cin >> c;
            if (c == '.')
                ans = flag ? ans + 1 : ans;
            else {
                ans += 0.5;
                flag = !flag;
            }
        }
    cout << ans;
}
