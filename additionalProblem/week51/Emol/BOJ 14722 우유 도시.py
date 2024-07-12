# BOJ 14722 우유 도시
import sys

input = sys.stdin.readline


def sol(n, city):
    dp = [[[0] * 3 for _ in range(n + 1)] for _ in range(n + 1)]

    for i in range(1, n + 1):
        for j in range(1, n + 1):
            currentMilk = city[i - 1][j - 1]

            if currentMilk == 0:
                dp[i][j][0] = max(dp[i][j - 1][2] + 1, dp[i - 1][j][2] + 1)
            else:
                dp[i][j][0] = max(dp[i][j - 1][0], dp[i - 1][j][0])

            if currentMilk == 1 and dp[i][j][0] > dp[i][j][1]:
                dp[i][j][1] = max(dp[i][j - 1][0] + 1, dp[i - 1][j][0] + 1)
            else:
                dp[i][j][1] = max(dp[i][j - 1][1], dp[i - 1][j][1])

            if currentMilk == 2 and dp[i][j][1] > dp[i][j][2]:
                dp[i][j][2] = max(dp[i][j - 1][1] + 1, dp[i - 1][j][1] + 1)
            else:
                dp[i][j][2] = max(dp[i][j - 1][2], dp[i - 1][j][2])

    return max(dp[n][n])


if __name__ == "__main__":
    n = int(input())
    city = [list(map(int, input().strip().split())) for _ in range(n)]
    print(sol(n, city))
