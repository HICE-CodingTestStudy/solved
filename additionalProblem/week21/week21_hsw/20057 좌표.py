#BOJ 20057

import sys
input = sys.stdin.readline

def move(x, y, N):
    i=0
    if y>x-1 and y<= N-x:
        y-=1; i=0
    elif y>=x and y>N-x:
        x-=1; i=3
    elif y<=x-1 and y<N-x:
        x+=1; i=1
    elif y<x and y>=N-x:
        y+=1; i=2
    return (x, y, i)

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

left = [(-2,0,0.02),(-1,-1,0.1),(-1,0,0.07),(-1,1,0.01),(0,-2,0.05),(1,-1,0.1),(1,0,0.07),(1,1,0.01),(2,0,0.02),(0,-1,False)]
right = [(x, -y, z) for x,y,z in left]
down = [(-y, x, z) for x,y,z in left]
up = [(y, x, z) for x,y,z in left]
dic = {0:left, 1:down, 2:right, 3:up}
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]

N = int(input())
A = [list(map(int,input().split())) for _ in range(N)]
x, y = N//2, N//2


    
ans = 0
flag = False
while not flag:
    x, y, i = move(x, y, N-1)
    spray(x, y, dic[i])
    if not x and not y:
        flag = True
print(ans)

