# BOJ 21924 도시 건설
# kruskal 알고리즘 사용
import sys
input = sys.stdin.readline

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

def kruskal(n, edges):
    # 엣지를 가중치 순으로 정렬
    edges.sort(key=lambda x: x[2])
    
    # 유니온-파인드를 위한 초기 부모 및 랭크 설정
    parent = [i for i in range(n)]
    rank = [0] * n
    
    mst_weight = 0
    mst_edges = 0
    
    # 모든 엣지에 대해
    for u, v, weight in edges:
        set_u = find(parent, u)
        set_v = find(parent, v)
        
        # 사이클을 만들지 않는 엣지일 경우 MST에 추가
        if set_u != set_v:
            union(parent, rank, set_u, set_v)
            mst_weight += weight
            mst_edges += 1
            
            # MST가 완성되면 종료
            if mst_edges == n - 1:
                break
    
    # 모든 노드가 연결되지 않았을 경우
    if mst_edges != n - 1:
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
        
    ans = kruskal(n, edges)
    print(ans)