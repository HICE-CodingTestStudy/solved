
#include <iostream>

using namespace std;

#define MOD 1000000000

int N, dp[101][10][1024];
long long res;

int main()
{
    cin >> N;

    for (int i = 1; i < 10; i++)
        dp[1][i][1 << i] = 1;

    for (int n = 2; n < N + 1; n++)
        for (int end = 0; end < 10; end++)
            for (int k = 0; k < 1024; k++) {
                // used : 이번에 end를 사용했을 때의 수(이미 사용 했다면 해당 비트가 1일 수 있음)
                // k : end를 사용하기 전의 수
                int used = k | (1 << end);
                if (end == 0)
                    dp[n][end][used] = (dp[n][end][used] + dp[n - 1][1][k]) % MOD;
                else if (end == 9)
                    dp[n][end][used] = (dp[n][end][used] + dp[n - 1][8][k]) % MOD;
                else
                    dp[n][end][used] = (dp[n][end][used] + dp[n - 1][end - 1][k] + dp[n - 1][end + 1][k]) % MOD;
            }

    for (int i = 0; i < 10; i++)
        res = (res + dp[N][i][1023]) % MOD;
    
    cout << res << endl;
}
