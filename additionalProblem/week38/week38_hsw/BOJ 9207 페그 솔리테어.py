# BOJ 9207 페그 솔리테어

import sys
input = sys.stdin.readline

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def dfs(depth):
    global minCnt, minMove
    
    pin = []
    for i in range(5):
        for j in range(9):
            if maps[i][j] == 'o':
                pin.append((i, j))
                
    L = len(pin)                
    if L < minCnt:
        minCnt, minMove = L, depth
    
    for x, y in pin:
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            # 넘기전, 넘은 후 핀 위치 모두 범위 안
            if 0 <= nx < 5 and 0 <= ny < 9:
                if 0 <= nx + dx[i] < 5 and 0 <= ny + dy[i] < 9:
                    # 넘을수 있다면
                    if maps[nx][ny] == 'o' and maps[nx + dx[i]][ny + dy[i]] == '.':
                        # 뛰어넘음
                        maps[nx][ny] = '.'
                        maps[nx + dx[i]][ny + dy[i]] = 'o'
                        maps[x][y] = '.'
                        dfs(depth + 1)
                        # 뛰어넘기 전 상태로 되돌리기
                        maps[nx][ny] = 'o'
                        maps[nx + dx[i]][ny + dy[i]] = '.'
                        maps[x][y] = 'o'

n = int(input())
for _ in range(n):
    maps = [list(map(str, input().rstrip())) for _ in range(5)]
    input()
    minCnt, minMove = 9, 9
    dfs(0)
    print(minCnt, minMove)
