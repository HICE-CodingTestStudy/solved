import sys
n = int(input())
nums = []

nums = list(map(int, sys.stdin.readline().split()))
memo = [1 for _ in range(n)]

for i in range(1, n):
    for j in range(i):
        if nums[j] > nums[i]:  # 감소
            memo[i] = max(memo[i], memo[j]+1)
        else:
            continue
print(max(memo))
