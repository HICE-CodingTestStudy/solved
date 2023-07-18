import sys
import math

n = int(input())
parent = [-1 for _ in range(n+1)]
height = [0 for _ in range(n+1)]


def find(u):
    if parent[u] == -1:
        return u
    parent[u] = find(parent[u])
    return parent[u]


def union(u, v):
    u = find(u)
    v = find(v)
    if (u == v):
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


place = [[]]  # [ x, y ]
for i in range(n):
    x, y = map(float, sys.stdin.readline().split())
    place.append([x, y])

graph = [[-1, -1, -1]]  # [ i, j, 가중치(길이) ]
for i in range(1, n+1):
    for j in range(i+1, n+1):
        graph.append([i, j,
                      round(math.sqrt((place[i][0]-place[j][0])**2+(place[i][1]-place[j][1])**2), 3)])
graph.sort(key=lambda x: x[2])

cost = 0
for i in range(1, len(graph)):
    if union(graph[i][0], graph[i][1]):
        cost += graph[i][2]

print(round(cost, 2))
