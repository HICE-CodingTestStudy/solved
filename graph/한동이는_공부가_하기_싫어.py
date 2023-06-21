# 해설지 참조

import sys

input = sys.stdin.readline

n = int(input())
graph = [0]
result = 0

for i in range(1, n + 1):
    graph.append(int(input()))


def DFS(node):
    visited[node] = True
    if not visited[graph[node]]:
        DFS(graph[node])


for i in range(1, n + 1):
    visited = [False] * (n + 1)
    DFS(i)
    result.append(visited.count(True))

print(result.index(max(result)))
