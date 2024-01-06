# BOJ 1202
import sys
input = sys.stdin.readline
import heapq

N, K = map(int, input().split())
jew = []
sack = []
for i in range(N):
    heapq.heappush(jew, list(map(int, input().split())))
for j in range(K):
    sack.append(int(input()))
sack.sort()

ans = 0
tmp = []

for bag in bags:
    while jew and (bag >= jew[0][0]):
        heapq.heappush(tmp, -heapq.heappop(jew)[1])
    if tmp:
        ans -= heapq.heappop(tmp)
    elif not jew:
        break
print(ans)
