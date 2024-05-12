#BOJ 1325
import sys
input = sys.stdin.readline
from collections import deque

N, M = map(int, input().split())
maps = [[] for _ in range(N+1)]

for _ in range(M):
    A, B = map(int, input().split())
    maps[B].append(A)

def bfs(start):
    q = deque()
    q.append(start)
    cnt = 0
    visited = [False] * (N+1)
    visited[start] = True
    while q:
        cur = q.popleft()
        for next in maps[cur]:  # 현재 노드에 연결된 모든 노드에 대해
            if not visited[next]:  # 방문하지 않은 노드라면
                visited[next] = True  # 방문 처리
                q.append(next)  # 큐에 추가
                cnt += 1  # 감염된 컴퓨터 수 증가
    return cnt


# 각 컴퓨터를 시작점으로 하는 BFS 실행하여 결과 저장
ans = []
for start in range(1, len(maps)):
    ans.append(bfs(start))

# 결과에서 최대 값 찾기
max_infected = max(ans)

# 최대 값과 일치하는 인덱스 출력
for i in range(len(ans)):
    if max_infected == ans[i]:
        print(i+1)
