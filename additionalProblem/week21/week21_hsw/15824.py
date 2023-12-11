#BOJ 15824

import sys
input = sys.stdin.readline
mod = int(1e9 + 7)

# 1,2,3,4,5 라는 배열에서
# 5를 최대값으로 갖는 조합 -> 2^4-1
# X를 최대값으로 갖는 조합 -> 2^N-1
# 모든 조합들의 최댓값 합 - 모든 조합들의 최솟값 합
# sum (2^i)-(2^(N-1-i))
N = int(input())
sco = list(map(int,input().split()))
sco.sort()

# 제곱 최적화
pow = {i:1 for i in range(N)}
for i in range(1, N):
    pow[i] = 2 * pow[i-1] % mod    # 0:1, 1:2, 2:4, 3:8, 4:16...

ans = 0
for i in range(N):
    ans += sco[i] * pow[i]
    ans -= sco[i] * pow[N-1-i]
    ans %= mod
print(ans)
