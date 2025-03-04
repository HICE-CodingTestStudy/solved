# BOJ 2854 문제 출제
import sys

input = sys.stdin.readline
MOD = 1000000007

if __name__ == "__main__":
    n = int(input())
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))

    dp = {}

    dp[b[0]] = a[0] % MOD
    if b[0] > 0:
        dp[b[0] - 1] = (dp.get(b[0] - 1, 0) + b[0]) % MOD

    for i in range(1, n - 1):
        b2 = b[i]
        dp2 = {}
        # f : 모든 경우의 수의 합
        # g : 각 애매의 가능한 가지수의 합 (eme * ways)
        f, g = 0, 0

        for eme, ways in dp.items():
            f = (f + ways) % MOD
            g = (g + eme * ways) % MOD

        # 1. 고정
        key = b2
        dp2[key] = (dp2.get(key, 0) + a[i] * f) % MOD

        # 2. 이전 애매
        dp2[key] = (dp2.get(key, 0) + g) % MOD

        # 3. 현재 애매
        if b2 > 0:
            key2 = b2 - 1
            dp2[key2] = (dp2.get(key2, 0) + b[i] * f) % MOD

        dp = dp2

    ans = 0
    for eme, ways in dp.items():
        ans = (ans + ways * (a[n - 1] + eme)) % MOD

    print(ans)
