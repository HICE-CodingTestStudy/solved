# BOJ 17271 리그 오브 레전설 (small)
import sys

input = sys.stdin.readline

MOD = 1_000_000_007

if __name__ == "__main__":
    n, m = map(int, input().split())
    d = n // m

    dp = [0] * (n + 1)
    dp[0] = 1

    for i in range(1, n + 1):
        dp[i] = dp[i - 1] % MOD
        if i >= m:
            dp[i] = (dp[i] + dp[i - m]) % MOD

    print(dp[n])
