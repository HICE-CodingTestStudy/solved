# BOJ 18223 민준이와 마산 그리고 건우
import sys, heapq
input = sys.stdin.readline

def dij(start):
    distance = [INF for _ in range(v+1)]
    distance[start] = 0 
    q = []
    heapq.heappush(q, (0, start))
    while q:
        dist, node = heapq.heappop(q)
        if dist > distance[node]:
            continue
        for next_node, next_dist in graph[node]:
            cost = next_dist + dist
            if cost < distance[next_node]:
                distance[next_node] = cost
                heapq.heappush(q, (cost, next_node))
    return distance
    
if __name__ == "__main__":
    INF = int(1e9)
    
    v, e, p = map(int,input().split())
    
    graph = [[] * (v+1) for _ in range(v+1)]
    
    for _ in range(e):
        a, b, c = map(int, input().split())
        graph[a].append((b, c))
        graph[b].append((a, c))
    d_array = dij(1)
    a, c = d_array[p], d_array[v]
    next_array = dij(p)
    b = next_array[v]
    
    print("SAVE HIM") if (a+b == c) else print("GOOD BYE")
    
        

