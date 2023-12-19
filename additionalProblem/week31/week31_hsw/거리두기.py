from collections import deque

def bfs(row, col, place):
    visited = [[0] * 5 for _ in range(5)]

    dx = [0, 0, -1, 1]
    dy = [1, -1, 0, 0]

    q = deque()
    q.append([row, col, 0])
    visited[row][col] = 1

    while q:
        x, y, dist = q.popleft()

        nd = dist + 1

        if nd >= 3:
            break

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if 0 <= nx < 5 and 0 <= ny < 5 and visited[nx][ny] == 0:
                visited[nx][ny] = 1

                if dist <= 2 and place[nx][ny] == 'P':
                    return False
                if place[nx][ny] == 'O' and nd == 1:
                    q.append([nx, ny, dist + 1])

    return True


def solution(places):
    answer = []

    for place in places:
        flag = 1

        for i in range(5):
            for j in range(5):
                if place[i][j] == 'P':
                    if not bfs(i, j, place):
                        flag = 0

                if not flag:
                    break

            if not flag:
                break
        answer.append(flag)

    return answer
