import sys
import heapq

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def dijkstra(count):
    queue = []
    heapq.heappush(queue, (graph[0][0], 0, 0))
    distance[0][0] = 0

    while (True):
        cost, x, y = heapq.heappop(queue)

        if x == N-1 and y == N-1:
            print(f'Problem {count}: {cost}')
            break

        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if nx < 0 or nx >= N or ny < 0 or ny >= N:
                continue

            n_cost = cost+graph[nx][ny]
            if n_cost < distance[nx][ny]:
                distance[nx][ny] = n_cost
                heapq.heappush(queue, (n_cost, nx, ny))


count = 0
while True:
    graph = []
    count += 1
    N = int(input())
    distance = [[1e9]*N for _ in range(N)]

    if N == 0:
        break

    for _ in range(N):
        graph.append(list(map(int, sys.stdin.readline().split())))
    dijkstra(count)
