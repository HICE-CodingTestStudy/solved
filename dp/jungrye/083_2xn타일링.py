n = int(input())
memo = [0 for _ in range(1001)]

memo[0] = 0
memo[1] = 1
memo[2] = 2

if n == 1 or n == 2:
    print(memo[n])
else:
    for i in range(3, n+1):
        memo[i] = memo[i-1]+memo[i-2]
    print(memo[n] % 10007)
