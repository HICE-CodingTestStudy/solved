#BOJ 10711 모래성
import sys, copy
input = sys.stdin.readline
from collections import deque

dx = [1, 1, 0, -1, -1, -1, 0, 1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

# main
if __name__ == "__main__":
    h, w = map(int,input().split())
    q = deque()
    field = []
    for i in range(h):
        field.append(list(input().rstrip()))
        for j in range(w):
            # 모래성 없는 애들만 큐에 삽입
            if field[i][j] == '.':
                field[i][j] = 0
                q.append((i, j, 0))
            else:
                field[i][j] = int(field[i][j])
    
    # 감소시키면서 0에 도달한 애들만 카운트
    while q:
        x, y, cnt = q.popleft()
        for dir in range(8):
            nx, ny = x + dx[dir], y + dy[dir]
            if 0<= nx < h and 0<= ny < w:
                field[nx][ny] -= 1
                # 0값에 도달한 애들만 카운트하므로, q가 무한으로 늘어나지 않음.
                if field[nx][ny] == 0:
                    q.append((nx, ny, cnt + 1))
    print(cnt)
    