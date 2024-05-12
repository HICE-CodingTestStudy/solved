#BOJ 1245
import sys
input = sys.stdin.readline
from collections import deque

N, M = map(int, input().split())
maps = [list(map(int, list(input().split(' ')))) for _ in range(N)]
visited = [[False] * M for _ in range(N)]
ans = 0
dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

def bfs(a, b):
    global ans
    flag = True
    q = deque([[a, b]])

    while q:
        x, y = q.popleft()
        for i in range(8):
            nx = x + dx[i]; ny = y + dy[i]
            if 0 <= nx < N and 0 <= ny < M:
                if maps[nx][ny] == maps[x][y]:
                    if not visited[nx][ny]:
                        visited[nx][ny] = True
                        q.append([nx, ny])
                elif maps[nx][ny] > maps[x][y]:
                    flag = False

    if flag:
        ans += 1


for i in range(N):
    for j in range(M):
        if not visited[i][j]:
            visited[i][j] = True
            bfs(i, j)

print(ans)