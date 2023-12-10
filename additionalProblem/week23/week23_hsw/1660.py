#BOJ 1660
import sys, fractions
input = sys.stdin.readline

N = int(input())
balls = []
b=0; i=1
while b < N:
    b += (i * (i+1))//2
    balls.append(b)
    i+=1
    
# dp : 사면체의 개수
# dp[i-ball] : i-ball 개의 대포알에서 생길 수 있는 사면체의 개수
dp = [int(1e9)] * (N+1)
for i in range(1, N + 1):
    for ball in balls:
        if ball >= i:
            if ball == i:
                dp[i] = 1
            break
        dp[i] = min(dp[i], 1 + dp[i - ball])
print(dp[N])
    

