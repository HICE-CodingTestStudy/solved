import sys

N = int(input())
X = list(map(int, sys.stdin.readline().split()))

newX = sorted(list(set(X)))

for x in X:
    left = 0
    right = len(newX)-1
    while (left <= right):
        mid = (left+right)//2
        if x == newX[mid]:
            print(mid, end=' ')
            break
        elif x <= newX[mid]:
            right = mid-1
        else:
            left = mid+1
