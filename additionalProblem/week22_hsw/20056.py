#BOJ 20056
import sys
input = sys.stdin.readline

N, M, K = map(int, input().split())
balls = []; maps = [[[] for _ in range(N)] for _ in range(N)]
dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

for _ in range(M):
    r, c, m, s, d = list(map(int, input().split()))
    balls.append([r-1, c-1, m, s, d])

for _ in range(K):
    while balls:
        r, c, m, s, d = balls.pop()
        nr = (r + s * dx[d]) % N  
        nc = (c + s * dy[d]) % N
        maps[nr][nc].append([m, s, d])

    for r in range(N):
        for c in range(N):
            L = len(maps[r][c])
            if L > 1:
                msum, ssum, cnt_odd, cnt_even= 0, 0, 0, 0
                while maps[r][c]:
                    m, s, d = maps[r][c].pop()
                    msum += m; ssum += s
                    if d % 2:
                        cnt_odd += 1
                    else:
                        cnt_even += 1
                if cnt_odd == L or cnt_even == L:
                    nd = [0, 2, 4, 6]
                else:
                    nd = [1, 3, 5, 7]
                if msum // 5:
                    for d in nd:
                        balls.append([r, c, msum // 5, ssum // L, d])

            if L == 1:
                balls.append([r, c] + maps[r][c].pop())
ans = 0
for ball in balls:
    ans += ball[2]
print(ans)