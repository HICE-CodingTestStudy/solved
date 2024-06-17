# BOJ 17780 새로운 게임
import sys

input = sys.stdin.readline
dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]


def move(pieceNum):
    x, y, d = pieces[pieceNum]
    if pieceNum != chessBoard[x][y][0]:
        return False

    nx, ny = x + dx[d], y + dy[d]

    # 파랑 + 맵밖
    if not (0 <= nx < n and 0 <= ny < n) or board[nx][ny] == 2:
        if d == 0 or d == 1:
            nd = (d + 1) % 2
        else:
            nd = (d - 1) % 2 + 2
        pieces[pieceNum][2] = nd
        nx, ny = x + dx[nd], y + dy[nd]
        if not (0 <= nx < n and 0 <= ny < n) or board[nx][ny] == 2:
            return False

    # 빨강 + 흰색
    chessSet = chessBoard[x][y][:]
    chessBoard[x][y] = []

    if board[nx][ny] == 1:
        chessSet.reverse()

    for i in chessSet:
        chessBoard[nx][ny].append(i)
        pieces[i][:2] = [nx, ny]

    # 종료조건
    return len(chessBoard[nx][ny]) >= 4


if __name__ == "__main__":

    n, k = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(n)]
    chessBoard = [[[] for _ in range(n)] for _ in range(n)]
    pieces = [0 for _ in range(k)]

    for i in range(k):
        x, y, d = map(int, input().split())
        chessBoard[x - 1][y - 1].append(i)
        pieces[i] = [x - 1, y - 1, d - 1]

    cnt = 1
    while cnt <= 1000:
        for i in range(k):
            if move(i):
                print(cnt)
                sys.exit()
        cnt += 1
    print(-1)
