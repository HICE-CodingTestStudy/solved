import sys
from collections import deque
T = int(input())
for _ in range(T):
    # N: 노드 수, M: 간선 수
    N, M = map(int, input().split())
    graph = [[] for _ in range(N+1)]
    visited = [0 for _ in range(N+1)]
    for _ in range(M):
        a, b = map(int, sys.stdin.readline().split())
        graph[a].append(b)
        graph[b].append(a)

    count = 0
    queue = deque([1])
    visited[1] = 1
    while (len(queue) != 0):
        index = queue.popleft()
        for i in graph[index]:
            if visited[i] == 0:
                queue.append(i)
                visited[i] = 1
                count += 1
    print(count)
