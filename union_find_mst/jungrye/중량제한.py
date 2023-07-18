import sys

N, M = map(int, input().split())

parent = [-1 for _ in range(N+1)]
height = [0 for _ in range(N+1)]
visited = [False for _ in range(N+1)]


def find(u):
    if parent[u] == -1:
        return u
    parent[u] = find(parent[u])
    return parent[u]


def union(u, v):
    visited[u] = True
    visited[v] = True
    u = find(u)
    v = find(v)
    if u == v:
        return False
    if height[v] > height[u]:
        temp = v
        v = u
        u = temp
    parent[v] = u
    height[v] = 0
    if height[v] == height[u]:
        height[u] += 1
    return True


graph = []
for _ in range(M):
    A, B, C = map(int, sys.stdin.readline().split())
    if A > B:
        temp = A
        A = B
        B = temp
    graph.append([A, B, C])

f1, f2 = map(int, input().split())

graph.sort(key=lambda x: x[2], reverse=True)

# 큰것부터 고르는데, 공장 1,2를 모두 고름 + 이어져있으면 스탑
max_weight = 1000000000
for i in range(M):
    if visited[f1] == True and visited[f2] == True and find(f1) == find(f2):
        break
    if union(graph[i][0], graph[i][1]):
        max_weight = graph[i][2]

print(max_weight)
