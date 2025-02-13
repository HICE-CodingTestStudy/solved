# BOJ 15817 배수공사
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n, x = map(int, input().split())
    l = [0] * (n + 1)
    c = [0] * (n + 1)
    for i in range(1, n + 1):
        l[i], c[i] = map(int, input().split())
     
    dp = [[0] * (x + 1) for _ in range(2)]
    dp[0][0] = 1

    for i in range(1, n + 1):
        cur = i % 2      
        prev = 1 - cur 
        for j in range(x + 1):
            dp[cur][j] = 0  

            for k in range(c[i] + 1):
                length = l[i] * k
                if j >= length:
                    dp[cur][j] += dp[prev][j - length]
                else:
                    break

    print(dp[n % 2][x])
    