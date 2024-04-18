# BOJ 22945 팀빌딩 
import sys
input = sys.stdin.readline

n = int(input())
nums = list(map(int, input().split()))

l, r, ans = 0, n-1, 0

while l<r:
    ans = max(ans, (r-l-1) * min(nums[l], nums[r]))
    if nums[l] < nums[r]:
        l += 1
    else:
        r -= 1
print(ans)
