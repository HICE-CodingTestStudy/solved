#BOJ 14267 회사 문화 1
import sys
input = sys.stdin.readline

n, m = map(int, input().split())
nums = [0] + list(map(int, input().split()))
applicant = [0] * (n+1)

for _ in range(m):
    idx, cnt = map(int, input().split())
    applicant[idx] += cnt

for i in range(2, n+1):
    applicant[i] += applicant[nums[i]]

print(*applicant[1:])
