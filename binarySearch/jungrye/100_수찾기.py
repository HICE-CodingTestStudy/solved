import sys

N = int(input())
A = list(map(int, sys.stdin.readline().split()))
A.sort()
M = int(input())
nums = list(map(int, sys.stdin.readline().split()))

for i in nums:
    left = 0
    right = N-1
    printed = 0
    while (left <= right):
        mid = (left+right)//2
        if (A[mid] == i):
            print(1)
            printed = 1
            break
        elif (A[mid] < i):
            left = mid+1
        else:
            right = mid-1
    if printed == 0:
        print(0)
