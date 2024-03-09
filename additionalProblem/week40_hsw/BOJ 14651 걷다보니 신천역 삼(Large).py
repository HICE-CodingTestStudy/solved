# BOJ 14651 걷다보니 신천역 삼(Large)
import sys
input = sys.stdin.readline

n = int(input())
dp = [0] * (n+1)
dp[1] = 0
# dp[2] = 2 dp[3] = 6 dp[4] = 18 dp[5] = 54
if n>=2:
    dp[2] = 2
    for i in range(3,n+1):
        dp[i] = (dp[i-1]*3) % 1000000009

print(dp[n])
