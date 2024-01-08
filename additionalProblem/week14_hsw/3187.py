#boj 3187

import sys
input = sys.stdin.readline
from collections import deque

R, C = map(int, input().split())
maps = [list(input()) for _ in range(R)]
visited = [[False] * C for _ in range(R)]

dx = [1, -1, 0, 0]     #상 하 좌 우
dy = [0, 0, 1, -1]

q = deque()
v, k = 0, 0

def bfs(x, y):
    q.append([x,y])    
    visited[x][y] = 1
    v_cnt, k_cnt = 0, 0
    while q:
        x, y =  q.popleft()
        if maps[x][y] == 'v':
            v_cnt += 1
        elif maps[x][y] == 'k':
            k_cnt += 1
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < R and 0 <= ny < C:
                if maps[nx][ny] != '#' and not visited[nx][ny]:
                    visited[nx][ny] = True
                    q.append([nx, ny])
    if v_cnt >= k_cnt:
        k_cnt = 0
    else:
        v_cnt = 0
    return [v_cnt, k_cnt]

for i in range(R):
    for j in range(C):
        if maps[i][j] != '#' and not visited[i][j]:
            v_cnt, k_cnt = bfs(i, j)
            v += v_cnt
            k += k_cnt
print(k, v)
            
        