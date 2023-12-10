#BOJ 21610

import sys
input = sys.stdin.readline

N, M = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(N)]
moves = [tuple(map(int, input().split())) for _ in range(M)]

dy = (0, -1, -1, -1, 0, 1, 1, 1)
dx = (-1, -1, 0, 1, 1, 1, 0, -1)

clouds = [(N-1, 0), (N-1, 1), (N-2, 0), (N-2, 1)] # 구름 좌표
for d, s in moves:
    temp = []
    for y, x in clouds:
        # 1. D방향으로, s만큼 이동
        ny = (y + dy[d-1] * s) % N 
        nx = (x + dx[d-1] * s) % N
        # 2. 이동된 각 구름자리에 비 추가
        A[ny][nx] += 1 
        temp.append((ny, nx))

    
    for y, x in temp:
        # 3,4. 물복사버그 
        water = 0
        for d in range(4):
            ny = y + dy[2*d+1]
            nx = x + dx[2*d+1]
            if ny < 0 or nx < 0 or ny >= N or nx >= N: 
                continue
            elif A[ny][nx] > 0: 
                water += 1
        A[y][x] += water

    # 5.
    temp2 = []
    for y in range(N):
        for x in range(N):
            if (y, x) in temp or A[y][x] < 2:
                continue
            A[y][x] -= 2
            temp2.append((y, x))
    clouds = temp2 

result = 0
for y in range(N):
    for x in range(N):
        result += A[y][x]
print(result)





