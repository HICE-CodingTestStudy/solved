# BOJ 17244 아맞다우산
import sys
from collections import deque

input = sys.stdin.readline


def sol(n, m, board):
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    s, e = None, None
    items = []

    for i in range(m):
        for j in range(n):
            if board[i][j] == "S":
                s = (i, j)
            elif board[i][j] == "E":
                e = (i, j)
            elif board[i][j] == "X":
                items.append((i, j))

    num_items = len(items)

    q = deque()
    q.append((s[0], s[1], 0))
    visited = set()
    visited.add((s[0], s[1], 0))

    steps = 0
    while q:
        for _ in range(len(q)):
            x, y, collected = q.popleft()

            if (x, y) == e and collected == (1 << num_items) - 1:
                return steps

            for dx, dy in directions:
                nx, ny = x + dx, y + dy

                if 0 <= nx < m and 0 <= ny < n and board[nx][ny] != "#":
                    n_collected = collected

                    if (nx, ny) in items:
                        n_collected |= 1 << items.index((nx, ny))

                    if (nx, ny, n_collected) not in visited:
                        visited.add((nx, ny, n_collected))
                        q.append((nx, ny, n_collected))

        steps += 1

    return -1


if __name__ == "__main__":
    n, m = map(int, input().split())
    board = [list(input().strip()) for _ in range(m)]
    print(sol(n, m, board))
