# BOJ 2230
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
A = []
for i in range(N):
    A.append(int(input()))
A.sort()
ans = int(2e9)
l, r = 0, 0
while l<=r and r<N:
    diff = A[r] - A[l]
    if diff < M:
        r += 1
    else:
        ans = min(ans, diff)
        l += 1
print(ans)