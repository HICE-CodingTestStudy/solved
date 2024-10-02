# BOJ 1484 일요일 아침의 데이트
import sys, heapq

input = sys.stdin.readline

n, m = map(int, input().split())
board = [list(input().rstrip()) for _ in range(n)]

sx, sy = -1, -1

for i in range(n):
    for j in range(m):
        if board[i][j] == "S":
            sx, sy = i, j
            break
    if sx != -1 and sy != -1:
        break

q = [(0, 0, sx, sy)]

dir = [(-1, 0), (1, 0), (0, -1), (0, 1)]

visited = [[False] * m for _ in range(n)]
visited[sx][sy] = True

while q:
    trash_count, near_trash_count, y, x = heapq.heappop(q)

    if board[y][x] == "F":
        print(trash_count, near_trash_count)
        break

    for dx, dy in dir:
        ny = y + dy
        nx = x + dx

        if 0 <= nx < m and 0 <= ny < n and not visited[ny][nx]:
            visited[ny][nx] = True

            if board[ny][nx] == "g":
                heapq.heappush(q, (trash_count + 1, near_trash_count, ny, nx))

            elif board[ny][nx] == ".":
                near_trash = 0
                for adj_dx, adj_dy in dir:
                    adj_y = ny + adj_dy
                    adj_x = nx + adj_dx
                    if 0 <= adj_x < m and 0 <= adj_y < n and board[adj_y][adj_x] == "g":
                        near_trash = 1
                        break
                heapq.heappush(q, (trash_count, near_trash_count + near_trash, ny, nx))

            else:
                heapq.heappush(q, (trash_count, near_trash_count, ny, nx))
