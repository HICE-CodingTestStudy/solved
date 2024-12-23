import sys
from heapq import heappop, heappush

input = sys.stdin.readline


# 유니온 파인드 구현 (부모 노드 찾기)
def find(parent, x):
    if parent[x] != x:
        parent[x] = find(parent, parent[x])
    return parent[x]


# 유니온 연산 (두 집합 합치기)
def union(parent, rank, a, b):
    root_a = find(parent, a)
    root_b = find(parent, b)

    if root_a != root_b:
        if rank[root_a] > rank[root_b]:
            parent[root_b] = root_a
        elif rank[root_a] < rank[root_b]:
            parent[root_a] = root_b
        else:
            parent[root_b] = root_a
            rank[root_a] += 1


def kruskal(n, edges):
    parent = [i for i in range(n + 1)]
    rank = [0] * (n + 1)
    mst_weight = 0
    mst_edges = []

    for weight, u, v in edges:
        if find(parent, u) != find(parent, v):
            union(parent, rank, u, v)
            mst_weight += weight
            mst_edges.append((weight, u, v))
            if len(mst_edges) == n - 1:
                break

    if len(mst_edges) == n - 1:
        return mst_weight, mst_edges
    else:
        return 0, []


def mst_game(n, k, edges):
    result = []
    edges = [(i + 1, u, v) for i, (u, v) in enumerate(edges)]

    for _ in range(k):
        mst_weight, mst_edges = kruskal(n, edges)
        result.append(mst_weight)

        if mst_weight == 0:
            result.extend([0] * (k - len(result)))
            break

        # 최소 가중치 간선 제거
        min_edge = min(mst_edges, key=lambda x: x[0])
        edges.remove(min_edge)

    return result


if __name__ == "__main__":
    n, m, k = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(m)]

    result = mst_game(n, k, edges)
    print(f"{' '.join(map(str, result))}")
