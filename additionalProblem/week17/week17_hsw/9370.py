import sys
input = sys.stdin.readline
import heapq

INF = int(1e9)

# 다익스트라 알고리즘을 구현한 함수
def dijkstra(start):
    
    q = []
    distances = [INF] * (n+1)
    
    heapq.heappush(q,(0,start))
    distances[start] = 0

    while q:
        cur_distance, cur_node = heapq.heappop(q)

        # 이미 처리한 노드 무시
        if distances[cur_node] < cur_distance:
            continue

        for neighbor, weight in graph[cur_node]:
            distance = cur_distance + weight

            if distance < distances[neighbor]:
                distances[neighbor] = distance
                heapq.heappush(q, (distance, neighbor))
                
    return distances

# 입력 처리
test_case = int(input())
results = []

for _ in range(test_case):
    n, m, t = map(int, input().split())
    s, g, h = map(int, input().split())
    #그래프 초기화
    graph = [[] for _ in range(n+1)]

    # 간선 정보 입력
    for _ in range(m):
        a, b, d = map(int, input().split())
        graph[a].append((b, d))
        graph[b].append((a, d))

    destinations = [int(input()) for _ in range(t)]

    # 1. 출발지 시작, 다익스트라 최소거리 구함    
    s_distances = dijkstra(s)
    # 2. H를 시작으로 다익스트라 최소거리 구함
    h_distances = dijkstra(h)
    # 3. G를 시작으로 다익스트라 최소거리 구함
    g_distances = dijkstra(g)

    val_destinations = []
    # 4. 출발지~G까지의 최소거리 + G~H의 거리 + H~목적지까지의 최소거리 구함
    for dest in destinations:
        # 5. 목적지 중에서 1. == 4.이면 G~H 간선을 지나는 값이 최소거리가 되어 정답
        if s_distances[g] + g_distances[h] + h_distances[dest] == s_distances[dest] or \
            s_distances[h] + h_distances[g] + g_distances[dest] == s_distances[dest]:
            val_destinations.append(dest)
    val_destinations.sort()

    #결과 출력
    for val_dest in val_destinations:
        print(val_dest, end = ' ')
    print()
