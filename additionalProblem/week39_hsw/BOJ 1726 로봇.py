# BOJ 1726 로봇
import sys
input = sys.stdin.readline
from collections import deque

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
rotate = ((2, 3), (2, 3), (0, 1), (0, 1))

m, n = map(int, input().split())
maps = [list(map(int, input().split())) for _ in range(m)]
start_x, start_y, start_dir = map(int, input().split())
end_x, end_y, end_dir = map(int, input().split())

# 1씩 빼줌
start_x -= 1; start_y -= 1; start_dir -= 1
end_x -= 1; end_y -= 1; end_dir -= 1

visited = [[[0] * 4 for _ in range(n)]for _ in range(m)]
visited[start_x][start_y][start_dir] = 1

q = deque()
q.append((start_x, start_y, start_dir, 0))

while q:
    x, y, dir, cnt = q.popleft()

    if x == end_x and y == end_y and dir == end_dir:
        print(cnt)
        break
    
    for k in range(1, 4):
        nx, ny, nd = x + dx[dir] * k, y + dy[dir] * k, dir
        if not (0 <= nx < m and 0 <= ny < n) or maps[nx][ny]:
            break
        if visited[nx][ny][nd]:
            continue
        q.append((nx, ny, nd, cnt + 1))
        visited[nx][ny][nd] = 1
    
    for next_dir in rotate[dir]:
        if visited[x][y][next_dir]:
            continue
        q.append((x, y, next_dir, cnt + 1))
        visited[x][y][next_dir] = 1
