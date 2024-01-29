import sys

input = sys.stdin.readline
INF = sys.maxsize

n = int(input())
dp = [[INF] * (n + 1) for _ in range(n + 1)]

while True:
    a, b = map(int, input().split())

    if a == -1 and b == -1:
        break

    dp[a][b] = 1
    dp[b][a] = 1

for i in range(1, n + 1):
    dp[i][i] = 0

for k in range(1, n + 1):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if dp[i][j] > dp[i][k] + dp[k][j]:
                dp[i][j] = dp[i][k] + dp[k][j]

result = []
for i in range(1, n + 1):
    result.append(max(dp[i][1:]))

point = min(result)
print(point, result.count(point))
for i, p in enumerate(result):
    if p == point:
        print(i + 1, end = ' ')
