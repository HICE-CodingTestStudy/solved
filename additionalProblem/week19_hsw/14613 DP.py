#BOJ 14613 in DP

import sys
input = sys.stdin.readline

W, L, D = map(float, input().split())

rating = [0.0]*5

dp = [[False]*3001 for _ in range(21)]  # dp[game][Point] = possibllity

dp[0][2000] = 1;

for i in range(1,21):
    for j in range(1000,3000):
        
        if(dp[i-1][j]==0): continue
        else:
            dp[i][j+50] += dp[i-1][j] * W
            dp[i][j-50] += dp[i-1][j] * L
            dp[i][j] += dp[i-1][j] * D

for i in range(1000, 3000):
    if (1000<= i <=1499):
        rating[0] += dp[20][i]
    elif (1500<= i <=1999):
        rating[1] += dp[20][i]
    elif (2000<= i <=2499):
        rating[2] += dp[20][i]
    elif (2500<= i <=2999):
        rating[3]+= dp[20][i]
    else:
        continue
        
rating[4] = W**20

for rate in rating:
    print(f"{rate:.8f}")