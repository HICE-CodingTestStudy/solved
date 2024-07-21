# BOJ 22955 고양이 도도의 탈출기
import sys
from heapq import heappop, heappush

input = sys.stdin.readline

dir = [[0, 1], [0, -1]]


def bfs():
    while q:
        cnt, x, y = heappop(q)
        if cnt > visited[x][y]:
            continue

        if floors[x][y] == "E":
            print(visited[x][y])
            return

        if floors[x][y] == "F":
            for dx, dy in dir:
                nx, ny = x + dx, y + dy

                if not (0 <= nx < n and 0 <= ny < m):
                    continue

                if floors[nx][ny] == "D":
                    continue

                if visited[nx][ny] > cnt + 1:
                    visited[nx][ny] = cnt + 1
                    heappush(q, [cnt + 1, nx, ny])

            if x + 1 < n and floors[x + 1][y] == "L" and visited[x + 1][y] > cnt + 5:
                visited[x + 1][y] = cnt + 5
                heappush(q, [cnt + 5, x + 1, y])

        elif floors[x][y] == "L":
            for dx, dy in dir:
                nx, ny = x + dx, y + dy

                if not (0 <= nx < n and 0 <= ny < m):
                    continue

                if floors[nx][ny] == "D":
                    continue

                if visited[nx][ny] > cnt + 1:
                    visited[nx][ny] = cnt + 1
                    heappush(q, [cnt + 1, nx, ny])

            if x - 1 >= 0 and floors[x - 1][y] != "D" and visited[x - 1][y] > cnt + 5:
                visited[x - 1][y] = cnt + 5
                heappush(q, [cnt + 5, x - 1, y])
            if x + 1 < n and floors[x + 1][y] == "L" and visited[x + 1][y] > cnt + 5:
                visited[x + 1][y] = cnt + 5
                heappush(q, [cnt + 5, x + 1, y])

        else:
            nx, ny = floors[x][y][0], floors[x][y][1]

            if visited[nx][ny] > cnt + 10:
                visited[nx][ny] = cnt + 10
                heappush(q, [cnt + 10, nx, ny])

    print("dodo sad")


if __name__ == "__main__":
    n, m = map(int, input().split())
    floors = []
    hole = []
    q = []
    sx, sy = 0, 0

    for i in range(n):
        floor = list(input().strip())
        for j in range(m):
            if floor[j] == "C":
                floor[j] = "F"
                sx, sy = i, j
                heappush(q, [0, sx, sy])

            elif floor[j] == "X":
                hole.append((i, j))
        floors.append(floor)

    for x, y in hole:
        if floors[x][y] != "X":
            continue

        tmp = []
        tmp.append([x, y])
        nx = x

        while True:
            nx += 1
            if nx >= n or floors[nx][y] != "X":
                break

            tmp.append([nx, y])

        if nx < n and floors[nx][y] == "D":
            for tx, ty in tmp:
                floors[tx][ty] = "D"

        else:
            for tx, ty in tmp:
                if nx < n:
                    floors[tx][ty] = [nx, y]

    visited = [[int(1e9)] * m for _ in range(n)]
    visited[sx][sy] = 0

    bfs()
