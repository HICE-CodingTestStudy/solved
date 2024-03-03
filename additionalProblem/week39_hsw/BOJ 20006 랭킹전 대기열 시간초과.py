# BOJ 20006 랭킹전 대기열
import sys
input = sys.stdin.readline
from collections import deque

# 함수
def printing_result(room, state):
    if state == 'W':
        print('Wating!')
    elif state == 'S':
        print('Started!')
    room.sort(key = lambda x : x[1])
    for pl, pn in room:
        print(pl, pn)
    room.clear()

# 입력
p, m = map(int, input().split())
players = deque()
for i in range(p):
    temp = input().split()
    l, n = int(temp[0]), temp[1]
    players.append((l, n))
room = []
while True:
    if players:
        player_level, player_name = players.popleft()
        if len(room) == 0:
            room.append((player_level, player_name))

        else:
            if room[0][0]-10 <= player_level <= room[0][0]+10:
                room.append((player_level, player_name))
                if len(room) == m:
                    printing_result(room, 'S')
            else:
                players.append((player_level, player_name))

    else:
        if 0 < len(room) < m:
            printing_result(room, 'W')
        break
