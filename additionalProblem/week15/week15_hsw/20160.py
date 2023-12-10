#boj 20160
import sys
input = sys.stdin.readline
import heapq

INF = int(1e9)
n,m = map(int,input().split())
graph = [[] for _ in range(n+1)]

def dijkstra(s, e):
    q = []
    dist = [INF for _ in range(n+1)]
    dist[s] = 0
    heapq.heappush(q, (0, s))
    while q:
        d, v = heapq.heappop(q)
        if d > dist[v]:
            continue
        for u, w in graph[v]:
            if dist[u] > dist[v] + w:
                dist[u] = dist[v] + w
                heapq.heappush(q, (dist[u], u))
    return dist[e]

for _ in range(m):
    a,b,c = map(int,input().split())
    graph[a].append((b,c))
    graph[b].append((a,c))
arr = list(map(int, input().split()))
s = int(input())

answer = INF
total = 0
prev = arr[0]

for i in range(10):
    yog_mom = dijkstra(prev, arr[i])
    if yog_mom == INF:    
        continue
    total += yog_mom
    prev = arr[i]
    me = dijkstra(s, arr[i])
    if total>= me:
        answer = min(answer, arr[i])
if answer == INF:
    print(-1)
else:
    print(answer)



