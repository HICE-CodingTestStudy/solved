import sys
from collections import defaultdict

input = sys.stdin.readline


def DFS(node, cnt):
    visited[node] = True

    for adj_node in graph[node]:
        if visited[adj_node] == False:
            cnt = DFS(adj_node, cnt + 1)

    return cnt


T = int(input())
for _ in range(T):
    N, M = map(int, input().split())
    graph = defaultdict(list)
    visited = [False] * (N + 1)

    for _ in range(M):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    print(DFS(1, 0))
