#boj 16234
import sys
input = sys.stdin.readline
from collections import deque

dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]

N, L, R = map(int, input().split())

area = [list(map(int, list(input().split()))) for _ in range(N)]

def bfs(ix, iy):
    q = deque()
    temp = []
    q.append((ix, iy))
    temp.append((ix, iy))
    while q:
        x, y = q.popleft()
        for dir in range(4):
            nx = x + dx[dir]; ny = y + dy[dir]
            if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny]:
                if L <= abs(area[nx][ny]-area[x][y]) <= R:
                    visited[nx][ny] = True
                    q.append((nx, ny))
                    temp.append((nx, ny))
    return temp

ans = 0

while True:
    visited = [[False] * (N+1) for _ in range(N+1)]
    flag = False
    for i in range(N):
        for j in range(N):
            if not visited[i][j]:
                visited[i][j] = True
                place = bfs(i, j)

                if len(place) > 1:
                    flag = True
                    num = sum([area[x][y] for x, y in place])//len(place)
                    for x, y in place:
                        area[x][y] = num
    if not flag:
        break
    ans += 1
print(ans)
