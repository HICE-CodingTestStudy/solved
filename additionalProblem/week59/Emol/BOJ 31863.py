# BOJ 31863 내진 설계
import sys
from collections import deque

input = sys.stdin.readline

dir = [(1, 0), (0, 1), (-1, 0), (0, -1)]

if __name__ == "__main__":
    n, m = map(int, input().split())
    board = [list(input().strip()) for _ in range(n)]
    durability = [[0] * m for _ in range(n)]
    collapsed = set()
    buildings = 0
    q = deque()
    sx, sy = -1, -1

    for i in range(n):
        for j in range(m):
            if board[i][j] == "@":
                sx, sy = i, j
            elif board[i][j] == "*" or board[i][j] == "#":
                buildings += 1

    for dx, dy in dir:
        for k in range(1, 3):
            nx = sx + dx * k
            ny = sy + dy * k

            if not (0 <= nx < n and 0 <= ny < m):
                break

            if board[nx][ny] == "|":
                break

            durability[nx][ny] += 1

            if board[nx][ny] == "*" or board[nx][ny] == "#":

                if (nx, ny) not in collapsed:

                    if board[nx][ny] == "*" and durability[nx][ny] >= 1:
                        collapsed.add((nx, ny))
                        q.append((nx, ny))

                    elif board[nx][ny] == "#" and durability[nx][ny] >= 2:
                        collapsed.add((nx, ny))
                        q.append((nx, ny))

    while q:
        x, y = q.popleft()

        for dx, dy in dir:
            nx = x + dx
            ny = y + dy

            if not (0 <= nx < n and 0 <= ny < m):
                continue

            if board[nx][ny] == "|":
                continue

            durability[nx][ny] += 1

            if board[nx][ny] == "*" or board[nx][ny] == "#":

                if (nx, ny) not in collapsed:

                    if board[nx][ny] == "*" and durability[nx][ny] >= 1:
                        collapsed.add((nx, ny))
                        q.append((nx, ny))

                    elif board[nx][ny] == "#" and durability[nx][ny] >= 2:
                        collapsed.add((nx, ny))
                        q.append((nx, ny))

    print(len(collapsed), buildings - len(collapsed))
