#BOJ 17391
import sys
input = sys.stdin.readline
from collections import deque

dx = [0, 1]
dy = [1, 0]

N, M = map(int, input().split())
maps = []
for _ in range(N):
    maps.append(list(map(int, input().split())))
visited = [[False]* M for _ in range(N)]

q = deque()
q.append((0, 0, 0))
visited[0][0] = True

while q:
    x, y, cnt = q.popleft()

    # 종료 지점 도달, 출력
    if x == N-1 and y == M - 1:
        print(cnt)
        break

    # 부스터 감안한 BFS
    for i in range(2):
        for j in range(1, maps[x][y] + 1):
            nx, ny = x + j * dx[i], y + j * dy[i]
            if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny]:
                q.append((nx, ny, cnt + 1))
                visited[nx][ny] = True
