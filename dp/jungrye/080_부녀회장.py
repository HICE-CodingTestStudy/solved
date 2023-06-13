T = int(input())

for _ in range(T):
    k = int(input())  # 층수(1~14)
    n = int(input())  # 호수(1~14)
    memo = [i+1 for i in range(n)]
    # print(memo)
    for i in range(1, k+1):
        for j in range(1, n):
            memo[j] = memo[j]+memo[j-1]
            # print(memo)
    print(memo[-1])
