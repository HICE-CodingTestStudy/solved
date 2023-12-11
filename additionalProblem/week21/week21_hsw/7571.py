#BOJ 7571

import sys
input = sys.stdin.readline

N, M = map(int, input().split())
X, Y = [], []

for _ in range(M):
    x, y= map(int, input().split())
    X.append(x); Y.append(y)
    
midx, midy = sorted(X)[M//2], sorted(Y)[M//2]

ans = 0
for i in range(M):
    ans += abs(midx-X[i]) + abs(midy-Y[i])
print(ans)