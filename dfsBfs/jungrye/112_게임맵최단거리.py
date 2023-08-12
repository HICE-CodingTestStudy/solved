from collections import deque


def solution(maps):
    answer = 0

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    def bfs(x, y):
        queue = deque()
        queue.append((x, y))
        while len(queue) != 0:
            x, y = queue.popleft()
            for i in range(4):
                nextX = x+dx[i]
                nextY = y+dy[i]
                if 0 <= nextX < len(maps) and 0 <= nextY < len(maps[0]) and maps[nextX][nextY] == 1:
                    maps[nextX][nextY] = maps[x][y] + 1
                    queue.append((nextX, nextY))
        return maps[len(maps)-1][len(maps[0])-1]
    answer = bfs(0, 0)
    if answer == 1:
        answer = -1
    return answer


print(solution(
    [[1, 0, 1, 1, 1],
     [1, 0, 1, 0, 1],
     [1, 0, 1, 1, 1],
     [1, 1, 1, 0, 1],
     [0, 0, 0, 0, 1]]))
print(solution([[1, 0, 1, 1, 1], [1, 0, 1, 0, 1], [
      1, 0, 1, 1, 1], [1, 1, 1, 0, 0], [0, 0, 0, 0, 1]]))
