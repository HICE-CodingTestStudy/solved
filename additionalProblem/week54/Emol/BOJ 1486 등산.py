# BOJ 1486 등산
import sys
import heapq

input = sys.stdin.readline

INF = int(1e9)

dir = [(0, 1), (0, -1), (1, 0), (-1, 0)]


def checkHeight(alp):
    if alp.isupper():
        return ord(alp) - ord("A")
    else:
        return ord(alp) - ord("a") + 26


def dij(start, n, m, heights, t):
    distances = [[INF] * m for _ in range(n)]
    distances[start[0]][start[1]] = 0
    pq = [(0, start[0], start[1])]  # (거리, x, y)

    while pq:
        current_dist, x, y = heapq.heappop(pq)

        if current_dist > distances[x][y]:
            continue

        for dx, dy in dir:
            nx, ny = x + dx, y + dy
            if 0 <= nx < n and 0 <= ny < m:
                diff = heights[nx][ny] - heights[x][y]
                if abs(diff) <= t:
                    if diff > 0:
                        time_cost = diff**2
                    else:
                        time_cost = 1
                    new_dist = current_dist + time_cost
                    if new_dist < distances[nx][ny]:
                        distances[nx][ny] = new_dist
                        heapq.heappush(pq, (new_dist, nx, ny))

    return distances


if __name__ == "__main__":
    n, m, t, d = map(int, input().split())
    height_map = [input().strip() for _ in range(n)]
    heights = [[checkHeight(height_map[i][j]) for j in range(m)] for i in range(n)]

    # 각 지점까지의 최소 시간 계산
    distances_from_start = dij((0, 0), n, m, heights, t)

    max_height = heights[0][0]

    # 각 지점에서 출발지점으로 돌아오는 최소 시간을 따로 계산
    for i in range(n):
        for j in range(m):
            if distances_from_start[i][j] == INF:
                continue
            # (i, j)에서 다시 (0, 0)으로 돌아오는 경로 계산
            distances_to_start = dij((i, j), n, m, heights, t)
            if distances_from_start[i][j] + distances_to_start[0][0] <= d:
                max_height = max(max_height, heights[i][j])

    print(max_height)
