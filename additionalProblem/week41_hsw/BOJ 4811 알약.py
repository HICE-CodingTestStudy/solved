# BOJ 4811 알약
import sys
input = sys.stdin.readline

dp = [[0] * 31 for _ in range(31)]

#dp[i][j] : h를 i개, w를 j개 먹었을 때 만들 수 있는 경우의 수

for i in range(31):
    dp[0][i] = 1

for i in range(1, 31):
    for j in range(i, 31):
        dp[i][j] = dp[i-1][j] + dp[i][j-1]

while True:
    t = int(input())
    if not t:
        break
    print(dp[t][t])
