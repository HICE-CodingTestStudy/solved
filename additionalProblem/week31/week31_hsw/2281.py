#BOJ 2281
import sys
input = sys.stdin.readline

n, m = map(int, input().split())
names = []
for _ in range(n):
    names.append(int(input()))
print(names)

#dp[i] = i번째까지 적었을때 남는 칸 수의 제곱의 합의 최소값
dp = [float('inf')] * (n+1)
dp[n] = 0

for i in range(1, n+1):
    L = 0
    for j in range(i, 0, -1):
        if j != i:
            L += 1
        L += names[j-1]

        if L>m:
            break

        if j-1>0:
            dp[i] = min(dp[i], dp[j-1] + (m-L)**2)

print(dp[n])
