# BOJ 2631 줄세우기
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    children = [int(input().strip()) for _ in range(n)]

    dp = [1] * n

    for i in range(n):
        for j in range(i):
            if children[j] < children[i]:
                dp[i] = max(dp[i], dp[j] + 1)

    lis_length = max(dp)

    result = n - lis_length
    print(result)
