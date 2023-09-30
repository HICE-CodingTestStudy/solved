from collections import deque
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
rx, ry, bx, by = 0, 0, 0, 0  # 초기화
board = []
for i in range(N):
    board.append(list(input()))
    for j in range(M):
        if board[i][j] == 'R':
            rx, ry = i, j
        elif board[i][j] == 'B':
            bx, by = i, j

dx = [-1, 1, 0, 0]  # x축 움직임
dy = [0, 0, -1, 1]  # y축 움직임


def move(x, y, dx, dy):
    cnt = 0  # 이동 칸 수
    # 다음이 벽이거나 현재가 구멍일 때까지 
    while board[x+dx][y+dy] != '#' and board[x][y] != 'O':
        x += dx
        y += dy
        cnt += 1
    return x, y, cnt

def bfs(rx, ry, bx, by):
    q = deque()
    q.append((rx, ry, bx, by, 1))
    visited = []
    visited.append((rx, ry, bx, by))
    while q:
        for _ in range(len(q)):
            rx, ry, bx, by, cnt = q.popleft()
            if cnt > 10:
                print(-1)
                return

            if board[rx][ry] == 'O':
                print(cnt)
                return
            
            for i in range(4):
                nrx, nry, rcnt = move(rx, ry, dx[i], dy[i])
                nbx, nby, bcnt = move(bx, by, dx[i], dy[i])  
                if board[nbx][nby] != 'O':  # 실패 조건이 아니면
                    if board[nrx][nry] == 'O':  # 성공 조건
                        print(cnt)
                        return
                    if nrx == nbx and nry == nby: #겹치면
                        if rcnt > bcnt:  # 이동거리가 많은 것을
                            nrx -= dx[i]  # 한 칸 뒤로
                            nry -= dy[i]
                        else:
                            nbx -= dx[i]
                            nby -= dy[i]
                    if(nrx, nry, nbx, nby) not in visited:
                        q.append((nrx, nry, nbx, nby, cnt+1))
                        visited.append((nrx, nry, nbx, nby))
 
    print(-1)

bfs(rx, ry, bx, by)
