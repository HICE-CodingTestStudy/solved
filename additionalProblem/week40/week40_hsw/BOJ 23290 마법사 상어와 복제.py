# BOJ 23290 마법사 상어와 복제
import sys
from copy import deepcopy
input = sys.stdin.readline

# 좌표
dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]
dx4 = [-1, 0, 1, 0]
dy4 = [0, -1, 0, 1]

# 상어 움직임
def move_shark(x, y, fish_cnt, direction, locations):
    if len(direction) == 3:
        possible_answer.append(direction[:] + [fish_cnt])
        return

    for d in range(4):
        nx, ny = x + dx4[d], y + dy4[d]
        if 0<= nx < 4 and 0 <= ny < 4:
            direction.append(d)
            if (nx, ny) not in locations:
                locations.add((nx, ny))
                move_shark(nx, ny, fish_cnt + len(fish[nx][ny]), direction, locations)
                locations.remove((nx, ny))
            else:
                move_shark(nx, ny, fish_cnt, direction, locations)
            direction.pop()

# main
m, s = map(int, input().split())

fish = [[[] for _ in range(4)] for _ in range(4)]
for _ in range(m):
    fx, fy, d = map(int, input().split())
    fish[fx-1][fy-1].append(d-1)
    
sx, sy = map(int, input().split())
sx, sy = sx -1, sy -1

smell = [[0] * 4 for _ in range(4)]

for k in range(1, s+1):
    #복제
    fish_copy = deepcopy(fish)

    #물고기 이동
    fish_temp = [[[] for _ in range(4)] for _ in range(4)]
    for i in range(4):
        for j in range(4):
            if fish[i][j]:
                for d in fish[i][j]:
                    cnt = 0
                    while cnt < 8:
                        nx, ny = i + dx[d], j + dy[d]
                        if 0 <= nx < 4 and 0 <= ny < 4:
                            if not (nx == sx and ny == sy):
                                if smell[nx][ny] == 0:
                                    fish_temp[nx][ny].append(d)
                                    break
                        d = (d + 7) % 8
                        cnt += 1
                    if cnt == 8:
                        fish_temp[i][j].append(d)
    fish = fish_temp

    #상어 이동
    possible_answer = []
    location = set()
    move_shark(sx, sy, 0, [], location)
    possible_answer.sort(key = lambda x: (-x[3], x[0], x[1], x[2]))


    #상어 이동자리에 물고기 제거 및 냄새 남기기
    for i in range(3):
        sx += dx4[possible_answer[0][i]]
        sy += dy4[possible_answer[0][i]]
        if fish[sx][sy]:
            smell[sx][sy] = k
            fish[sx][sy] = []

    # 생선 복제 및 냄새 제거
    for i in range(4):
        for j in range(4):
            if smell[i][j]:
                if k == smell[i][j] + 2:
                    smell[i][j] = 0

            if fish_copy[i][j]:
                fish[i][j].extend(fish_copy[i][j])

# 물고기 수 계산
ans = 0
for i in range(4):
    for j in range(4):
        ans += len(fish[i][j])
print(ans)
