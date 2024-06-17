# BOJ 17208 카우버거 알바생
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    n, m, k = map(int, input().split())

    orders = [tuple(map(int, input().split())) for _ in range(n)]

    dp = [[[0] * (k + 1) for _ in range(m + 1)] for _ in range(n + 1)]

    for i in range(1, n + 1):
        x, y = orders[i - 1]
        for j in range(1, m + 1):
            for k in range(1, k + 1):
                if j >= x and k >= y:
                    dp[i][j][k] = max(dp[i - 1][j][k], dp[i - 1][j - x][k - y] + 1)
                else:
                    dp[i][j][k] = dp[i - 1][j][k]

    print(dp[n][m][k])
