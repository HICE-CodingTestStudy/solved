# BOJ 3109 »§Áý
import sys
input = sys.stdin.readline

dx = [-1, 0, 1]

def dfs(x, y):
    if y == c - 1:
        return True

    for dir in dx:
        nx, ny = x + dir, y + 1
        if 0 <= nx < r and 0 <= ny < c:
            if board[nx][ny] != 'x':
                board[nx][ny] = 'x'
                if dfs(nx, ny):
                    return True

    return False

if __name__ == "__main__":
    r, c = map(int, input().split())

    board = [list(input().rstrip()) for _ in range(r)]

    cnt = 0
    for i in range(r):
        if dfs(i, 0):
            cnt += 1 

    print(cnt)
