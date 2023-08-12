import sys

n = int(input())
if n == 1:
    print(int(input()))
    exit(0)
if n == 2:
    print(int(input())+int(input()))
    exit(0)

stairs = [0]
memo = [0 for _ in range(n+1)]
for _ in range(n):
    stairs.append(int(sys.stdin.readline()))
memo[0] = 0
memo[1] = stairs[1]
memo[2] = stairs[1]+stairs[2]

for i in range(3, n+1):
    memo[i] = max(memo[i-2]+stairs[i], memo[i-3]+stairs[i-1]+stairs[i])
print(memo[n])
