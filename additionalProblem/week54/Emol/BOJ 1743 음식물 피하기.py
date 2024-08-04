# BOJ 1743 음식물 피하기
import sys
from collections import deque

input = sys.stdin.readline

dir = [(1, 0), (0, 1), (-1, 0), (0, -1)]


def bfs(x, y):
    q = deque([(x, y)])
    trash[x][y] = 0
    size = 1

    while q:
        x, y = q.popleft()

        for dx, dy in dir:
            nx, ny = x + dx, y + dy

            if 0 <= nx < n and 0 <= ny < m:
                if trash[nx][ny] == 1:
                    q.append((nx, ny))
                    trash[nx][ny] = 0
                    size += 1
    return size


if __name__ == "__main__":
    n, m, k = map(int, input().split())

    trash = [[0] * m for _ in range(n)]

    for _ in range(k):
        r, c = map(int, input().split())
        trash[r - 1][c - 1] = 1

    max_size = 0
    for i in range(n):
        for j in range(m):
            if trash[i][j] == 1:
                max_size = max(max_size, bfs(i, j))

    print(max_size)
