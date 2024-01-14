# BOJ 2591 숫자카드
import sys
input = sys.stdin.readline

n = list(map(int, input().strip()))
L = len(n)
# dp[i][j] : i 까지의 숫자를 사용하여 숫자 j를 만들 수 있는 경우의 수
dp = [[0 for _ in range(35)] for _ in range(L)]
dp[0][n[0]] = 1

for i in range(1, L):
    for j in range(1, 35):
        # 이전 수 * 10 + 현재수 <= 34 성립하면 dp를 더해준다.
        next = 10 * j + n[i]
        if next <= 34:
            dp[i][next] += dp[i-1][j]
        # 이전 수에 현재수를 그대로 붙여 현재수에 더해준다
        dp[i][n[i]] += dp[i-1][j]

# 0 제외한 합
print(sum(dp[-1][1:]))
