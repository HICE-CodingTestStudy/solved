# BOJ 2616 소형기관차
import sys
input = sys.stdin.readline

n = int(input())
train = list(map(int, input().split()))
m = int(input())

sum_list = [0]
temp = 0
for i in range(n):
    temp += train[i]
    sum_list.append(temp)

dp = [[0] * (n+1) for _ in range(4)]
# dp[소형 기관차 번호][실어갈 객차 번호의 마지막부분] = 최대 운송승객수
for i in range(1, 4):
    for j in range(i*m, n+1):
        dp[i][j] = max(dp[i][j-1], dp[i-1][j-m] + sum_list[j] - sum_list[j-m])

print(dp[3][n])
