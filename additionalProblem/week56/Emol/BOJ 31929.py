# BOJ 31929 너 재능있어
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    wins = [0] + list(map(int, input().split()))
    m = int(input())
    loses = [0] + list(map(int, input().split()))
    k = int(input())


    dp = [[0] * (m + 1) for _ in range(n + 1)]
    
    for i in range(1, n+1):
        dp[i][0] = dp[i-1][0] + wins[i]
        
    for j in range(1, m+1):
        if dp[0][j-1] % k == 0:
            dp[0][j] = dp[0][j-1] - loses[j]
        else:
            dp[0][j] = dp[0][j-1] - min(dp[0][j-1] % k, loses[j])
    
    for i in range(1, n+1):
        for j in range(1, m+1):
            if dp[i][j-1] % k == 0:
                dp[i][j] = max(dp[i-1][j] + wins[i], dp[i][j-1] - loses[j])
            else:
                dp[i][j] = max(dp[i-1][j] + wins[i], dp[i][j-1] - min(dp[i][j-1] %k, loses[j]))
    
    print(dp[-1][-1])