import heapq

n, m = map(int, input().split())
pQueue = list(map(int, input().split()))
heapq.heapify(pQueue)

for i in range(m):
    tempSum = heapq.heappop(pQueue)
    tempSum += heapq.heappop(pQueue)
    heapq.heappush(pQueue, tempSum)
    heapq.heappush(pQueue, tempSum)

sum = 0
for i in range(len(pQueue)):
    sum += heapq.heappop(pQueue)
print(sum)
