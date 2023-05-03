import sys
import heapq

n = int(input())
pQueue = []
for i in range(n):
    value = list(map(int, sys.stdin.readline().split()))
    if value[0] == 0:
        if len(pQueue) == 0:
            print(-1, end=' ')
        else:
            print(-heapq.heappop(pQueue), end=' ')

    else:
        del value[0]
        for j in value:
            heapq.heappush(pQueue, -j)
