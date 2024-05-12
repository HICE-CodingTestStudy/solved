
#include<iostream>
using namespace std;

double W, L, D, ans[5], dp[21][3001];
 
int main(void) {
    cin >> W >> L >> D;
    dp[0][2000] = 1;

    for (int i = 1; i <= 20; i++)
        for (int j = 1000; j <= 3000; j++) {
            if (dp[i - 1][j] == 0) continue;
            dp[i][j - 50] += dp[i - 1][j] * L;
            dp[i][j + 50] += dp[i - 1][j] * W;
            dp[i][j] += dp[i - 1][j] * D;
        }
    
    for (int i = 1000; i <= 3000; i++)
        if (1000 <= i && i <= 1499) ans[0] += dp[20][i];
        else if (1500 <= i && i <= 1999) ans[1] += dp[20][i];
        else if (2000 <= i && i <= 2499) ans[2] += dp[20][i];
        else if (2500 <= i && i <= 2999) ans[3] += dp[20][i];
        else ans[4] += dp[20][i];

    for(auto a: ans)
        printf("%.8lf\n", a);
}
