#BOJ 25916
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
holes = list(map(int, input().split()))

left, right, current_sum, max_sum = 0, 0, 0, 0

while right < N:
    current_sum += holes[right]

    while current_sum > M:
        current_sum -= holes[left]
        left += 1

    max_sum = max(max_sum, current_sum)
    right += 1

print(max_sum)
