#boj 1113
import sys
input = sys.stdin.readline
from collections import deque

area = []
visited = []
dx = [1, 0, 0, -1]
dy = [0, 1, -1, 0]
ans = 0

# 해당 깊이에서 수영장이 형성되어있는지 확인
def bfs(x, y, depth):
    q = deque()
    q.append([x, y])
    pos = [[x, y]]      #수영장이 형성되는 장소 배열

    flag = False        #수영장이 형성되는지 확인하는 flag
    visited[x][y] = True
    
    while q:
        x, y = q.popleft()  #현 위치에서
        for dir in range(4):    # 상하좌우로 수영장 형성되는지 확인
            nx = x + dx[dir]; ny  = y + dy[dir]
            if 0 <= nx < N and 0 <= ny < M and area[nx][ny]:    #전체 범위 안에 있고
                if not visited[nx][ny] and area[nx][ny] <= depth:   #현 수위보다 해당 위치가 낮을경우
                    visited[nx][ny] = True      #방문 후 배열에 추가
                    q.append([nx,ny])
                    pos.append([nx, ny])
            else:   # 형성이 안되면
                flag = True
    if flag:        # 물을 채우지 못하므로 0 반환
        return 0
    else:           # 형성이 되면
        sum = 0     # 채워진 값 계산
        for i, j in pos:
            area[i][j] += 1
            sum += 1
        return sum
    
# 물 깊이를 한층씩 증가시키면서 주변 수영장 형성되었는지 확인
N,M = map(int, input().split())
area = [list(map(int, list(input().rstrip()))) for _ in range(N)]
for depth in range(1,10):
    visited = [[False] * M for _ in range(N)]
    for i in range(N):
        for j in range(M):
            if not visited[i][j] and area[i][j] <= depth:   
                ans += bfs(i, j, depth)

print(ans)
