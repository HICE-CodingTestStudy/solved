# BOJ 19236
# 깊은 복사 아이디어 참고 https://velog.io/@emplam27/%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EB%A6%AC%EC%8A%A4%ED%8A%B8%EC%9D%98-%EA%B9%8A%EC%9D%80%EB%B3%B5%EC%82%AC%EB%8A%94-deepcopy%EA%B0%80-%EB%B9%A0%EB%A5%BC%EA%B9%8C-slicing%EC%9D%B4-%EB%B9%A0%EB%A5%BC%EA%B9%8C
import sys
input = sys.stdin.readline

dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, -1, -1, -1, 0, 1, 1, 1]

board = [[None] for _ in range(4)]
fish = [[None] for _ in range(16)]
ans = 0

def dfs(x, y, dir, num):
    global ans, board, fish
    move(x, y)
    while True:
        nx, ny = x + dx[dir], y + dy[dir]
        if not 0<= nx < 4 or not 0<= ny < 4:
            ans = max(ans, num)
            return
        if not board[nx][ny]:
            x, y = nx, ny
            continue
        tempboard = [t[:] for t in board]
        tempfish = [t[:] for t in fish]

        fish[board[nx][ny][0]] = []
        board[nx][ny] = []
        dfs(nx,ny, tempboard[nx][ny][1], num + tempboard[nx][ny][0] + 1)
        board, fish = tempboard, tempfish
        x, y = nx, ny

def move(a, b):
    for i in range(16):
        if fish[i]:
            x, y = fish[i][0], fish[i][1]
            dir = board[x][y][1]
            ndir = dir
            while True:
                nx, ny = x + dx[dir], y + dy[dir]
                if not 0<= nx < 4 or not 0 <= ny < 4 or (a == nx and b == ny):
                    ndir = (ndir + 1) % 8
                    if ndir == dir:
                        break
                    continue
                if board[nx][ny]:
                    fish[board[nx][ny][0]] = [x, y]
                board[x][y] = board[nx][ny]
                board[nx][ny] = (i, ndir)
                fish[i] = [nx, ny]
                break

# 입력 처리
for i in range(4):
    temp = list(map(int, input().split())) 
    for j in range(4):
        board[i][j] = [temp[2*j], temp[2*j+1]-1]
        fish[temp[j]-1] = [i, j//2]

fish_num, dir = board[0][0][0], board[0][0][1]
fish[fish_num] = []
board[0][0] = []
dfs(0, 0, dir, fish_num + 1)
print(ans)

