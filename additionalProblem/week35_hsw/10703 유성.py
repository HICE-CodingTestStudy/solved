import sys
input = sys.stdin.readline

r, s = map(int, input().split())
maps = [list(input().rstrip()) for _ in range(r)]

move_cnt = 3001

for i in range(s):
    meteor, ground = 0, 0
    for j in range(r):
        if maps[j][i] == 'X':
            meteor = j + 1
        if maps[j][i] == '#':
            ground = j + 1
            break
    if meteor != 0 and ground != 0:
        move_cnt = min(move_cnt, ground - meteor - 1)

for i in range(s):
    for j in range(r - 1, -1, -1):
        if maps[j][i] == 'X':
            maps[j + move_cnt][i] = 'X'
            maps[j][i] = '.'

for i in range(r):
    print(''.join(maps[i]))

