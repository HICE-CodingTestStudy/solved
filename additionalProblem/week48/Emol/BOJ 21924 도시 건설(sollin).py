# BOJ 21924 도시 건설
# sollin 알고리즘 사용
import sys
input = sys.stdin.readline
from collections import defaultdict

def find(parent, i):
    if parent[i] == i:
        return i
    else:
        parent[i] = find(parent, parent[i])
        return parent[i]

def union(parent, rank, x, y):
    root_x = find(parent, x)
    root_y = find(parent, y)
    
    if root_x != root_y:
        if rank[root_x] > rank[root_y]:
            parent[root_y] = root_x
        elif rank[root_x] < rank[root_y]:
            parent[root_x] = root_y
        else:
            parent[root_y] = root_x
            rank[root_x] += 1

def sollin(n, edges):
    # 유니온-파인드를 위한 초기 부모 및 랭크 설정
    parent = [i for i in range(n)]
    rank = [0] * n
    
    mst_weight = 0
    num_components = n
    
    while num_components > 1:
        cheapest = [-1] * n
        
        # 각 간선 집합에 대해  최소비용 간선 탐색
        for u, v, weight in edges:
            set_u = find(parent, u)
            set_v = find(parent, v)
            
            if set_u != set_v:
                if cheapest[set_u] == -1 or cheapest[set_u][2] > weight:
                    cheapest[set_u] = (u, v, weight)
                if cheapest[set_v] == -1 or cheapest[set_v][2] > weight:
                    cheapest[set_v] = (u, v, weight)
        
        # 최소비용의 간선을 MST에 추가
        added = False
        for node in range(n):
            if cheapest[node] != -1:
                u, v, weight = cheapest[node]
                set_u = find(parent, u)
                set_v = find(parent, v)
                
                if set_u != set_v:
                    union(parent, rank, set_u, set_v)
                    mst_weight += weight
                    num_components -= 1
                    added = True
        
        # 더이상 간선을 추가하지 못하면 루프 탈출
        if not added:
            break

    # 모든 노드가 연결되었는지 확인
    unique_components = len(set(find(parent, i) for i in range(n)))
    if unique_components != 1:
        return -1
    
    # 모든 엣지의 총 비용
    total_cost = sum(weight for _, _, weight in edges)
    
    # 절약된 비용은 총 비용에서 MST 비용을 뺀 값
    return total_cost - mst_weight


if __name__ == "__main__":
    n, m = map(int, input().split())
    edges = []
    for _ in range(m):
        a, b, c = map(int, input().split())
        edges.append((a-1, b-1, c))
        
    ans = sollin(n, edges)
    print(ans)
