# BOJ 11497 통나무 건너뛰기
import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    N = int(input())
    L = list(map(int,input().split()))
    L.sort()
    ans = 0
    for i in range(2, N):
        ans = max(ans, abs(L[i] - L[i-2]))
    print(ans)

    