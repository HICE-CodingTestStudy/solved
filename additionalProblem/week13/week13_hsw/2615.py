import sys
input = sys.stdin.readline

board = [list(map(int, input().split())) for _ in range(19)]

    #하, 우하, 우, 우상 
dx = [1, 1, 0, -1]
dy = [0, 1, 1,  1]

def sol(x, y):
    color = board[x][y]

    for i in range(4):
        cnt = 1
        nx = x + dx[i]
        ny = y + dy[i]

        while 0 <= nx < 19 and 0 <= ny < 19 and board[nx][ny] == color:
            cnt += 1
    
            if cnt == 5:
                #육목 확인
                # 첫 바둑알 이전에 더 놓여있는지 체크
                if 0 <= x-dx[i] < 19 and 0 <= y-dy[i] < 19 and board[x-dx[i]][y-dy[i]] == color:
                    break
                # 마지막 바둑알 이후에 더 놓여있는지 확인
                if 0 <= nx+dx[i] < 19 and 0 <= ny+dy[i] < 19 and board[nx+dx[i]][ny+dy[i]] == color:
                    break
                #오목 승리
                print(color)
                print(x+1, y+1)
                exit(0)

            #확인하던 방향으로 진전
            nx += dx[i]
            ny += dy[i]

for i in range(19):
    for j in range(19):
        if board[i][j] != 0:
            sol(i, j)
            
#어느쪽도 이기지 못한 경우
print(0)