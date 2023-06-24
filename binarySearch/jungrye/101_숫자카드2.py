import sys

N = int(input())
nList = list(map(int, sys.stdin.readline().split()))
M = int(input())
mList = list(map(int, sys.stdin.readline().split()))

nList.sort()
nDict = {}
for n in nList:
    if n not in nDict:
        nDict[n] = 1
    else:
        nDict[n] += 1
newNList = list(nDict.keys())

for m in mList:
    count = 0
    left = 0
    right = len(newNList)-1
    while (left <= right):
        mid = (left+right)//2
        if m == newNList[mid]:
            count = nDict[m]
            break
        elif m < newNList[mid]:
            right = mid-1
        else:
            left = mid+1
    print(count, end=' ')
