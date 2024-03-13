# BOJ 4179 불!
import sys
input = sys.stdin.readline
from collections import deque

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

r, c = map(int, input().split())
Jvisited = [[0] * c for _ in range(r)]
Fvisited = [[0] * c for _ in range(r)]
q_J = deque()
q_F = deque()

maps = []
for i in range(r):
    temp = list(input())
    for j in range(len(temp)):
        if temp[j] == 'J':
            Jvisited[i][j] = 1
            q_J.append((i, j))
            
        elif temp[j] == 'F':
            Fvisited[i][j] = 1
            q_F.append((i, j))
    maps.append(temp)

while q_F:
    x, y = q_F.popleft()
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < r and 0 <= ny < c:
            if maps[nx][ny] != '#' and not Fvisited[nx][ny]:
                Fvisited[nx][ny] = Fvisited[x][y] + 1
                q_F.append((nx, ny))

while q_J:
    x, y = q_J.popleft()
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < r and 0 <= ny < c:
            if maps[nx][ny] != '#' and not Jvisited[nx][ny]:
                if not Fvisited[nx][ny] or Fvisited[nx][ny] > Jvisited[x][y] + 1:
                    Jvisited[nx][ny] = Jvisited[x][y] + 1
                    q_J.append((nx, ny))
        else:
            print(Jvisited[x][y])
            exit(0)

print('IMPOSSIBLE')

