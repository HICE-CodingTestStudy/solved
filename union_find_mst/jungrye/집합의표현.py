# 0이면 union
# 1이면 find (같은집합인지)

import sys

N, M = map(int, input().split())
parent = [-1 for _ in range(N + 1)]
height = [0 for _ in range(N + 1)]


def find(u):
    if parent[u] == -1:
        return u
    parent[u] = find(parent[u])
    return parent[u]


def union(u, v):
    u = find(u)
    v = find(v)
    if u == v:
        return
    # 항상  u가 레벨 더 큼.
    if height[u] < height[v]:
        temp = u
        u = v
        v = temp
    parent[v] = u
    height[v] = -1
    if height[u] == height[v]:
        height[u] += 1


for i in range(M):
    c, a, b = map(int, sys.stdin.readline().split())
    if c == 0:
        union(a, b)
    if c == 1:
        if find(a) == find(b):
            print("yes")
        else:
            print("no")
