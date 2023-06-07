import sys

input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().split()))

arr.sort()
total = 0
sub_total = 0

for i in arr:
    sub_total += i
    total += sub_total

print(total)