import heapq
import sys
input = sys.stdin.readline

def dijkstra(start):
    D = [float('inf')] * (N+1)    #초기화
    D[start] = 0
    q = []								    # 최단 거리 테이블을 heap으로 구현
    heapq.heappush(q, (0, start))			# heap에 (가중치, 노드) 형식으로 삽입 
    while q:    #큐에 남아있는 노드가 없으면 종료
        dist, dest = heapq.heappop(q)		# 탐색할 노드, 거리 pop
        
        if D[dest] >= dist:					# 이미 최솟값 구했는지 확인
            for v, val in place[dest]:		# 연결된 노드들 확인
                if dist + val < D[v]:		# 가중치가 더 작은 값이면 갱신
                    D[v] = dist + val
                    heapq.heappush(q, (dist + val, v))    #다음 인접 거리를 계산하기 위해 큐에 삽입
    return D

N, M, X = map(int, input().split())

place = [[] for _ in range(N+1)]

for _ in range(M):
    s, e, Ti = map(int, input().split())
    place[s].append([e, Ti])
    
ans = dijkstra(X)
ans[0] = 0

for i in range(1, N+1):
    if i != X:
        res = dijkstra(i)
        ans[i] += res[X]

print(max(ans))