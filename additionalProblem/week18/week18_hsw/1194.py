#BOJ 1194

import sys
input = sys.stdin.readline
from collections import deque

N, M  = map(int, input().split())
# [x][y][key] 형태의 visited 리스트
# a~f 에 대해 000000~111111 로 비트마스킹
visited =[[[False] * (1 << 6) for _ in range(M)] for _ in range(N)]
maps = [list(map(str, input().rstrip())) for _ in range(N)]

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

for i in range(N):
    for j in range(M):
        if maps[i][j] == '0':
            maps[i][j] = '.'
            q = deque([(i, j, 0, 0)])
            visited[i][j][0] = True
            break
            
#BFS 구현
while q:
    x, y, cnt, key = q.popleft()
    if maps[x][y] == '1':
        print(cnt)
        break
    
    for i in range(4):
        nx = x + dx[i]; ny = y + dy[i];
        # 범위 안
        if 0 <= nx < N and 0 <= ny < M:
            # 방문한적 없음
            if not visited[nx][ny][key]:
                #벽은 패스
                #벽이 아닌, 진행 가능한 곳이면 append
                if maps[nx][ny] == '1' or maps[nx][ny] == '.':
                    visited[nx][ny][key] = True
                    q.append((nx, ny, cnt + 1, key))

                #열쇠를 만나면, 현재 key에 or(|)연산 통해 추가
                elif 'a' <= maps[nx][ny] <= 'f':
                    tmp_key = key | (1 << (ord(maps[nx][ny]) - ord('a')))
                    visited[nx][ny][key] = True
                    q.append((nx, ny, cnt + 1, tmp_key))

                #문을 만나면, and(&)연산 통해 열쇠 확인 후 진행. 
                elif 'A' <= maps[nx][ny] <= 'Z':
                    tmp_key = key & (1 << (ord(maps[nx][ny]) - ord('A')))
                    if tmp_key:
                        visited[nx][ny][key] = True
                        q.append((nx, ny, cnt + 1, key))

#루프를 다 돌았는데도 없다면, -1 출력
if maps[x][y] != '1':
    print(-1)
    