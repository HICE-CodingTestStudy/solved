#BOJ 1727
import sys
input = sys.stdin.readline

n, m = map(int,input().split())

men = sorted(list(map(int, input().split())))
women = sorted(list(map(int, input().split())))

dp = [[False] * (m+1) for _ in range(n+1)]

for man in range(1, n+1):
    for woman in range(1, m+1):
        #man == woman
        dp[man][woman] = dp[man-1][woman-1] + abs(men[man-1] - women[woman-1])
        if man > woman:
            dp[man][woman] = min(dp[man][woman], dp[man-1][woman])
        elif man < woman:
            dp[man][woman] = min(dp[man][woman], dp[man][woman-1])
print(dp[n][m])