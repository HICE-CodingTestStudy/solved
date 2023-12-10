#BOJ 11967
import sys
input = sys.stdin.readline
from collections import deque

N, M = map(int, input().split()) 
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

board = [[0] * (N+1) for _ in range(N+1)]  # 1: 불 켜짐, 2: 이동 가능
board[1][1] = 2
switchs = [[[] for _ in range(N+1)] for _ in range(N+1)]
for _ in range(M):
    x, y, a, b = map(int, input().split())
    switchs[x][y].append([a, b])

def BFS(x, y):
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 < nx <= N and 0 < ny <= N:
            if board[nx][ny] == 1:
                board[nx][ny] = 2
                q.append([nx, ny])
                BFS(nx, ny)

cnt = 1
q = deque([[1, 1]])
while q:
    x, y = q.popleft()
    for a, b in switchs[x][y]:
        for i in range(4):
            na, nb = a + dx[i], b + dy[i]
            if 0 < na <= N and 0 < nb <= N:
                if board[a][b] == 2:
                    break

                if board[na][nb] == 2:
                    cnt += 1
                    board[a][b] = 2
                    q.append([a, b])
                    BFS(a, b)
                    break
        if board[a][b] == 0:
            board[a][b] = 1
            cnt += 1
print(cnt)