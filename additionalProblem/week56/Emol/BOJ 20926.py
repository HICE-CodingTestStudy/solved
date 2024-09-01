# BOJ 20926 얼음 미로
import sys,heapq

input = sys.stdin.readline

def dij(sx, sy):
    dir = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    
    q = []
    heapq.heappush(q, (0, sx, sy))
    dist[sx][sy] = 0
    
    while q:
        d, cx, cy = heapq.heappop(q)
        
        if visited[cx][cy]:
            continue
        visited[cx][cy] = True
        
        for dx, dy in dir:
            check = slide(cx, cy, dx, dy)
            if check == -1:
                continue
            
            distance = 0
            for j in range(1, check + 1):
                next_cell = board[cx + j*dx][cy + j*dy]
                if next_cell == 'E':
                    break
                if next_cell.isdigit():
                    distance += int(next_cell)
            
            nx = cx + check*dx
            ny = cy + check*dy
            if not visited[nx][ny] and dist[nx][ny] > dist[cx][cy] + distance:
                dist[nx][ny] = dist[cx][cy] + distance
                if board[nx][ny] != 'E':
                    heapq.heappush(q, (dist[nx][ny], nx, ny))

def slide(x, y, dx, dy):
    count = 1
    while True:
        nx = x + dx*count
        ny = y + dy*count
        
        if nx < 0 or ny < 0 or nx >= h or ny >= w or board[nx][ny] == 'H':
            count = -1
            break
        if board[nx][ny] == 'R':
            count -= 1
            break
        if board[nx][ny] == 'E':
            break
        count += 1
    
    return count

w, h = map(int, input().split())
board = [list(input().strip()) for _ in range(h)]
dist = [[500*500*9] * w for _ in range(h)]
visited = [[False] * w for _ in range(h)]

sx, sy, ex, ey = -1, -1, -1, -1

for i in range(h):
    for j in range(w):
        cur = board[i][j]
        if cur == 'T':
            sx, sy = i, j
        elif cur == 'E':
            ex, ey = i, j

dij(sx, sy)
print(-1 if dist[ex][ey] == 500*500*9 else dist[ex][ey])
