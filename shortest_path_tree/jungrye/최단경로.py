import sys
import heapq

V, E = map(int, input().split())  # 정점, 간선 개수
K = int(input())  # 시작 정점

graph = [[] for _ in range(V+1)]


def dijkstra(graph, start):
    dist = [float('inf') for _ in range(V+1)]
    dist[start] = 0
    queue = []
    heapq.heappush(queue, [dist[start], start])

    while queue:
        curr_dist, curr_dest = heapq.heappop(queue)
        if dist[curr_dest] < curr_dist:
            continue
        for new_dest, new_dist in graph[curr_dest]:
            distance = curr_dist+new_dist
            if distance < dist[new_dest]:
                dist[new_dest] = distance
                heapq.heappush(queue, [distance, new_dest])
    return dist


for i in range(E):
    u, v, w = map(int, sys.stdin.readline().split())  # u->v 가중치 w
    graph[u].append([v, w])

answer = dijkstra(graph, K)
for i in range(1, V+1):
    if answer[i] == float('inf'):
        print('INF')
    else:
        print(answer[i])
