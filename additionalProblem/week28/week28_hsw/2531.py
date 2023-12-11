#BOJ 2531
import sys
input = sys.stdin.readline

N, d, k, c = map(int, input().split())
sushi = [int(input()) for _ in range(N)]
ans = 0
for i in range(N):
    if i+k > N:
        cnt = len(set(sushi[i:N] + sushi[:(i+k)%N] + [c]))
    else:
        cnt = len(set(sushi[i:i+k] + [c]))
    if ans < cnt:
        ans = cnt
print(ans)