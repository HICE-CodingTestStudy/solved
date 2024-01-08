
#include <iostream>
using namespace std;

int ans, t, w, k, plum[1001][3], count[1001][31][3];

int main(void) {
    cin >> t >> w;
    for(int i = 1; i <= t; i++) {
        cin >> k;
        plum[i][k] = 1;
    }

    count[1][0][1] = plum[1][1];
    count[1][1][2] = plum[1][2];
    ans = max(count[1][0][1], count[1][1][2]);

    for(int i = 2; i <= t; i++)
        for(int j = 0; j <= w; j++) {
            count[i][j][1] = max(count[i - 1][j][1], count[i - 1][j - 1][2]) + plum[i][1];
            count[i][j][2] = max(count[i - 1][j][2], count[i - 1][j - 1][1]) + plum[i][2];
            ans = max(max(ans, count[i][j][1]), count[i][j][2]);
        }

    cout << ans;
}
