# BOJ 20006 랭킹전 대기열
import sys
input = sys.stdin.readline

p, m = map(int, input().split())

rooms = []
for i in range(p):
    temp = input().split()
    l, n = int(temp[0]), temp[1]
    # 방 생성을 위한 flag
    flag = False
    # 방이 생성되어있다면, 확인 후 플레이어 추가
    for room in rooms:
        if len(room) < m:
            if room and room[0][0]-10 <= l <= room[0][0]+10:
                room.append([l, n])
                flag = True
                break
    # 방이 없다면, 방 생성
    if not flag:
        rooms.append([[l, n]])

for room in rooms:
    print('Started!' if len(room) == m else 'Waiting!')
    room.sort(key = lambda x : x[1])
    for player_level, player_name in room:
        print(player_level, player_name)
