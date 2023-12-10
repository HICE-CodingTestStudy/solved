#BOJ 1577
import sys
input = sys.stdin.readline

N, M = map(int,input().split())
dp = [[0] * (N+1) for _ in range(M+1)]
dp[0][0] = 1
const = []

K = int(input())
for i in range(K):
    a, b, c, d = map(int, input().split())
    const.append([a, b, c, d])

for x in range(M+1):
    for y in range(N+1):
        if x > 0:
            for a, b, c, d in const:
                if [y, x-1, y, x] == [a, b, c, d] or [y, x-1, y, x] == [c, d, a, b]:
                    break
            else:
                dp[x][y] += dp[x-1][y]
        if y > 0:
            for a, b, c, d in const:
                if [y-1, x, y, x] == [a, b, c, d] or [y-1, x, y, x] == [c, d, a, b]:
                    break
            else:
                dp[x][y] += dp[x][y-1]
print(dp[M][N])
