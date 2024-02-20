#BOJ 14225 부분수열의 합
import sys
input = sys.stdin.readline

n = int(input())
s = list(map(int, input().split()))
s.sort()

sum = 0
for i in s:
    if sum + 1 < i:
        break
    sum += i
print(sum + 1)
