# BOJ 17129 윌리암슨수액빨이딱따구리가 정보섬에 올라온 이유
import sys
input = sys.stdin.readline
from collections import deque

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]


if __name__ == '__main__':
    
    q = deque()
    n, m = map(int, input().split())
    a = [[0] * m for _ in range(n)]
    for i in range(n):
        temp = input()
        for j in range(m):
            a[i][j] = int(temp[j])
            if int(temp[j]) == 2:
                q.append((i, j, 0))
                a[i][j] = 1
    while q:
        x, y, cnt = q.popleft()
        for dir in range(4):
            nx, ny = x + dx[dir], y + dy[dir]
            if 0 <= nx < n and 0 <= ny < m:
                if a[nx][ny] == 1:
                    continue
                if a[nx][ny] in [3, 4, 5]:
                    print('TAK')
                    print(cnt+1)
                    sys.exit()
                q.append((nx, ny, cnt + 1))
                a[nx][ny] = 1
                        
    print('NIE')