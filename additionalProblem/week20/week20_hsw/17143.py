#BOJ 17143

import sys
input = sys.stdin.readline

def capture(j):
    for i in range(R):
        if maps[i][j]:
            size = maps[i][j][2]
            maps[i][j] = 0
            return size
    return 0

def move(maps):
    temp_map = [[0] * C for _ in range(R)]
    for x in range(R):
        for y in range(C):
            if maps[x][y]:
                speed, dir, size = maps[x][y]
                if dir == 0 or dir == 1:
                    speed = speed % (2 * (R-1))
                elif dir == 2 or dir == 3:
                    speed = speed % (2 * (C-1))
                nx = x; ny = y; distance = 0
                while distance < speed:
                    nx += dx[dir]; ny += dy[dir]
                    if nx < 0 or R <= nx or ny < 0 or C <= ny:
                        nx -= dx[dir]; ny -= dy[dir]
                        if dir == 0: dir = 1
                        elif dir == 1: dir = 0
                        elif dir == 2: dir = 3
                        elif dir == 3: dir = 2
                    else:
                        distance += 1

                if temp_map[nx][ny]:
                    if size > temp_map[nx][ny][2]:
                        temp_map[nx][ny] = (speed, dir, size)
                else:
                    temp_map[nx][ny] = (speed, dir, size)
    maps = temp_map
    return maps

R, C, M = map(int, input().split())

maps = [[0] * C for _ in range(R)]
dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]

for i in range(M):
    # row, col, speed, dir, size
    r, c, s, d, z = map(int, input().split())
    maps[r-1][c-1] = (s, d-1, z)

ans = 0
for i in range(C):
    ans += capture(i)
    maps = move(maps)

print(ans)