#BOJ 2666
import sys
input = sys.stdin.readline

cabinet = int(input())
open1, open2 = map(int, input().split())
inputcnt = int(input())
li = [int(input()) for _ in range(inputcnt)]

#dp : [depth][open1][open2]

dp = [[[-1] * (cabinet+1) for _ in range(cabinet+1)] for _ in range(inputcnt)]

def sol(depth, open1, open2):
    if depth == inputcnt:
        return 0

    if not dp[depth][open1][open2] == -1:
        return dp[depth][open1][open2]

    nopen1 = sol(depth+1, li[depth], open2) + abs(li[depth] - open1)
    nopen2 = sol(depth+1, open1, li[depth]) + abs(li[depth] - open2)
    dp[depth][open1][open2] = min(nopen1, nopen2)

    return dp[depth][open1][open2]

print(sol(0, open1, open2))

