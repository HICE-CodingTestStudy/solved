#BOJ 2240
import sys, fractions
input = sys.stdin.readline

T, W = map(int, input().split())
drops = [0] + [[int(input()) for _ in range(T)]]

# dp : T일때 W 번 이동하면 얻는 최대 자두 개수
#dp[t][w] = t-1 까지 w번 이하 최대 + t까지 w 번 이동 최대 자두 개수

dp = [[0] * (W + 1) for _ in range(T+1)]

dp[1][0] = drops[1]%2
dp[1][1] = drops[1]//2

for i in range(2, T+1):
    for j in range(W+1):
        if not j%2:
            temp = drops[t] % 2
        else:
            temp = drops[t] // 2

        dp[i][j] = max(dp[i-1][0:j+1])+temp

print(max(dp[-1]))
