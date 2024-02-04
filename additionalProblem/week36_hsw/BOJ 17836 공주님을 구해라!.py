#BOJ 17836 공주님을 구해라!
import sys
input = sys.stdin.readline
from collections import deque

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

n, m, t = map(int,input().split())

maps = []
for _ in range(n):
    maps.append(list(map(int, input().split())))

visited = [[0] * m for _ in range(n)]

ans = 10001
q = deque()
q.append((0, 0))
visited[0][0] = 1
while q:
    x, y = q.popleft()
    if (x, y) == (n-1, m-1):
        ans = min(visited[x][y]-1, ans)
    if maps[x][y] == 2:
        ans = visited[x][y]-1 + (n-1-x) + (m-1-y)

    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < n and 0 <= ny < m:
            if not visited[nx][ny]:
                if maps[nx][ny] == 0 or maps[nx][ny] == 2:
                    visited[nx][ny] = visited[x][y] + 1
                    q.append((nx, ny))

if ans > t:
    print('Fail')
else:
    print(ans)
