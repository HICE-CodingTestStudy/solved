# BOJ 11559
import sys
input = sys.stdin.readline
from collections import deque

field = [list(input().rstrip()) for _ in range(12)]

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

# 뿌요 가능한지 확인하는 bfs 함수
def check(i, j):
    q = deque()
    q.append((i, j))
    temp.append((i, j))
    while q:
        x, y = q.popleft()
        for idx in range(4):
            nx, ny = x + dx[idx], y + dy[idx]
            if 0<= nx <12 and 0<= ny <6:
                if not visited[nx][ny]:
                    if field[nx][ny] == field[x][y]:
                        visited[nx][ny] = True
                        q.append((nx, ny))
                        temp.append((nx, ny))

#중력으로 내리는 함수
def down():
    for j in range(6):
        # 뿌요 좌표 일괄 저장
        puyo = deque()
        for i in range(11, -1, -1):
            if field[i][j] != '.':
                puyo.append(field[i][j])
        # 저장된 좌표 일괄 입력
        for i in range(11, -1, -1):
            if puyo:
                field[i][j] = puyo.popleft()
            else:
                field[i][j] = '.'


# main
ans = 0
while True:
    flag = False
    visited = [[False] * 6 for _ in range(12)]
    for i in range(12):
        for j in range(6):
            if field[i][j] != '.' and not visited[i][j]:
                visited[i][j] = True
                temp = []    # 터뜨릴 부분 저장할 배열 temp
                check(i, j)
                
                if len(temp)>=4:    #뿌요 일어나면 빈공간으로 초기화
                    flag = True
                    for x, y in temp:
                        field[x][y] = '.'
    if not flag:
        break
    down()
    ans += 1
    
print(ans)
