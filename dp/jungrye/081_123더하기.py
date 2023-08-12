T = int(input())

for _ in range(T):
    n = int(input())
    # f(n) = f(n-3) + f(n-2) + f(n-1)

    if (n > 3):
        memo = [0 for _ in range(n+1)]
    else:
        memo = [0, 0, 0, 0]

    memo[1] = 1
    memo[2] = 2
    memo[3] = 4

    if n <= 3:
        print(memo[n])
    else:
        for i in range(4, n+1):
            memo[i] = memo[i-3]+memo[i-2]+memo[i-1]
        print(memo[n])
