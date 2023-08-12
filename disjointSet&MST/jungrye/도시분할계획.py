import sys

# 집 개수, 길 개수
N, M = map(int, input().split())
parent = [-1 for _ in range(N + 1)]
height = [0 for _ in range(N + 1)]
graph = []


def find(u):
    if parent[u] == -1:
        return u
    parent[u] = find(parent[u])
    return parent[u]


def union(u, v):
    u = find(u)
    v = find(v)
    if u == v:
        return False
    if height[v] > height[u]:
        temp = v
        v = u
        u = temp
    if height[v] == height[u]:
        height[u] += 1
    height[v] = 0
    parent[v] = u
    return True


for i in range(M):
    a, b, weight = map(int, sys.stdin.readline().split())
    graph.append([a, b, weight])
graph.sort(key=lambda x: x[2])

cost = 0
last = 0
for i in range(M):
    if union(graph[i][0], graph[i][1]):
        cost += graph[i][2]
        last = graph[i][2]


cost -= last
print(cost)
