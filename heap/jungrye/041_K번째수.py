import sys
import heapq

n, k = map(int, sys.stdin.readline().split())
heap = list(map(int, sys.stdin.readline().split()))
heapq.heapify(heap)
for i in range(k-1):
    heapq.heappop(heap)
print(heapq.heappop(heap))
