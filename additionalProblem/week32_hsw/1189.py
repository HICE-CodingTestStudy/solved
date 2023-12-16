#BOJ 1189
import sys
input = sys.stdin.readline

R, C, K = map(int, input().split())

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

maps = []
for _ in range(R):
    maps.append(list(input().rstrip()))

# (r-1, 0) -> (0, c-1) within K
ans = 0
def dfs(x, y, dist):
    global ans
    if dist == K and x == 0 and y == C-1:
        ans += 1
    else:
        maps[x][y] = 'T'
        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
            if 0 <= nx < R and 0 <= ny < C:
                if maps[nx][ny] == '.':
                    maps[nx][ny] = 'T'
                    dfs(nx, ny, dist + 1)
                    maps[nx][ny] = '.'
dfs(R-1, 0, 1)
print(ans)
