# BOJ 13565 침투
import sys
input = sys.stdin.readline
from collections import deque

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

m, n = map(int, input().split())
graph = [[] * n for _ in range(m)]
for i in range(m):
    temp = input().rstrip()
    for j in range(n):
        graph[i].append(int(temp[j]))

# x=0 인 모든 y값에 대하여 bfs 실행
for j in range(n):
    # 장애물시 패스
    if graph[0][j]:
        continue
    else:
        #bfs 실행
        q = deque()
        q.append((0, j))
        
        visited = [[False] * n for _ in range(m)]
        visited[0][j] = True
        
        while q:
            x, y = q.popleft()
            for idx in range(4):
                nx, ny = x + dx[idx], y + dy[idx]
                if 0<=nx<m and 0<=ny<n:
                    if graph[nx][ny] or visited[nx][ny]:
                        continue
                    
                    elif not visited[nx][ny] and not graph[nx][ny]:
                        # 최하단에 도착하면, 결과출력 후 강제 종료
                        if nx == m-1:
                            print('YES')
                            sys.exit(0)
                        visited[nx][ny] = True
                        q.append((nx, ny))

print('NO')