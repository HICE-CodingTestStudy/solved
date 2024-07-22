# BOJ 1600 말이 되고픈 원숭이
import sys
from collections import deque

input = sys.stdin.readline

dir = [[1, 0], [0, 1], [-1, 0], [0, -1]]
horse_dir = [[2, 1], [1, 2], [2, -1], [-2, 1], [-2, -1], [-1, -2], [-1, 2], [1, -2]]

if __name__ == "__main__":
    k = int(input())
    w, h = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(h)]

    q = deque()
    q.append((0, 0, 0))

    visited = [[[0] * (k + 1) for _ in range(w)] for _ in range(h)]
    visited[0][0][0] = 1
    ans = -1

    while q:
        x, y, z = q.popleft()

        if x == h - 1 and y == w - 1:
            ans = visited[x][y][z] - 1
            break

        for [dx, dy] in dir:
            nx, ny = x + dx, y + dy
            if (
                0 <= nx < h
                and 0 <= ny < w
                and not board[nx][ny]
                and not visited[nx][ny][z]
            ):
                q.append((nx, ny, z))
                visited[nx][ny][z] = visited[x][y][z] + 1

        if z < k:
            for [dx, dy] in horse_dir:
                nx, ny = x + dx, y + dy
                if (
                    0 <= nx < h
                    and 0 <= ny < w
                    and not board[nx][ny]
                    and not visited[nx][ny][z + 1]
                ):
                    q.append((nx, ny, z + 1))
                    visited[nx][ny][z + 1] = visited[x][y][z] + 1

    print(ans)
