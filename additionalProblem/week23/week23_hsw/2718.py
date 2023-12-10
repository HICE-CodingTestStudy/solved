#BOJ 2718
import sys
input = sys.stdin.readline

def sol(n, bit, dp):
    if n < 0:
        return 0
    if n < 1:
        return int(bit == 0)
    if dp[n][bit] != -1:
        return dp[n][bit]

    dp[n][bit] = 0
    if bit == 0:
        dp[n][bit] += sol(n - 1, 0, dp)
        dp[n][bit] += sol(n - 1, 3, dp)
        dp[n][bit] += sol(n - 1, 9, dp)
        dp[n][bit] += sol(n - 1, 12, dp)
        dp[n][bit] += sol(n - 2, 0, dp)
    if bit == 3:
        dp[n][bit] += sol(n - 1, 0, dp)
        dp[n][bit] += sol(n - 1, 12, dp)
    if bit == 6:
        dp[n][bit] += sol(n - 1, 9, dp)
    if bit == 9:
        dp[n][bit] += sol(n - 1, 0, dp)
        dp[n][bit] += sol(n - 1, 6, dp)
    if bit == 12:
        dp[n][bit] += sol(n - 1, 0, dp)
        dp[n][bit] += sol(n - 1, 3, dp)
    return dp[n][bit]


T = int(input())
for _ in range(T):
    N = int(input())
    dp = [[-1] * 13 for _ in range(501)]
    result = sol(N, 0, dp)
    print(result)
