import sys
from collections import defaultdict

input = sys.stdin.readline
graph = defaultdict(list)

c = int(input())
n = int(input())
visited = [False] * (c + 1)

for _ in range(n):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)


def DFS(node, cnt):
    visited[node] = True

    for adj_node in graph[node]:
        if visited[adj_node] == False:
            cnt = DFS(adj_node, cnt + 1)

    return cnt


print(DFS(1, 0))
