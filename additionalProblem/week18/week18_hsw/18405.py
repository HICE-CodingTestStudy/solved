#BOJ 18405

import sys
input = sys.stdin.readline
from collections import deque

N, K = map(int, input().split())
plate = []; virus = [] 
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

for i in range(N):
    plate.append(list(map(int, input().split())))
    for j in range(N):
        if plate[i][j]:
            virus.append((plate[i][j], i, j)) # 바이러스 타입, X, Y
virus.sort()

S, X, Y = map(int, input().split())
q = deque(virus)
cnt = 0
while q:
    if cnt == S:
        break
    for _ in range(len(q)):
        virus_type, x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0<= nx < N and 0 <= ny < N:
                if not plate[nx][ny]:
                    plate[nx][ny] = virus_type
                    q.append((virus_type, nx, ny))
    cnt += 1
print(plate[X-1][Y-1])
