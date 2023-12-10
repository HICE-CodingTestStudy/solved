#BOJ 20057

import sys
input = sys.stdin.readline

def spray(x, y, dir):
    global ans
    if y < 0:
        return
    out = 0
    for dx, dy, p in dir:
        nx = x + dx; ny = y + dy
        if p == 0:  
            accum = A[x][y] - out
        else:  
            accum = int(A[x][y] * p)
            out += accum
    
        if 0 <= nx < N and 0 <= ny < N:  
            A[nx][ny] += accum
        else:  
            ans += accum
    A[x][y] = 0

N = int(input())
A = [list(map(int,input().split())) for _ in range(N)]

left = [(-2,0,0.02),(-1,-1,0.1),(-1,0,0.07),(-1,1,0.01),(0,-2,0.05),(1,-1,0.1),(1,0,0.07),(1,1,0.01),(2,0,0.02),(0,-1,False)]
right = [(x, -y, z) for x,y,z in left]
down = [(-y, x, z) for x,y,z in left]
up = [(y, x, z) for x,y,z in left]
dic = {0:left, 1:down, 2:right, 3:up}
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]

x, y = N//2, N//2

i = 0; cnt = 0; ans = 0
flag = False
while not flag:
    i %=4
    if not i%2:
        cnt += 1
    for _ in range(cnt):
        x += dx[i]; y += dy[i]
        spray(x, y, dic[i])
        if not x and not y:
            flag = True
    i += 1
print(ans)