import sys

N = int(input())
weight = []
graph = []
P = []
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
        return False
    if height[v] > height[u]:
        temp = v
        v = u
        u = temp
    if height[v] == height[u]:
        height[v] += 1
    parent[v] = u
    height[v] = 0
    return True


# i번째 논에 우물을 팔 때 드는 비용
for i in range(1, N + 1):
    P.append([0, i, int(sys.stdin.readline())])
# weight.sort(key=lambda x: x[1])

# 각 줄에 N개의 수거 들어옴.
# i번째 논과 j번째 논을 연결하는데 드는 비용
for i in range(1, N + 1):
    graph.append(list(map(int, sys.stdin.readline().split())))

# 12 13 14
#    23 24
#       34
for i in range(1, N + 1):
    for j in range(i + 1, N + 1):
        P.append([i, j, graph[i - 1][j - 1]])

P.sort(key=lambda x: x[2])

cost = 0
for i in range(len(P)):
    if union(P[i][0], P[i][1]):
        cost += P[i][2]

print(cost)
