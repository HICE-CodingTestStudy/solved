#BOJ 1347
import sys
input = sys.stdin.readline

N = int(input())

dx = [1, 0, -1, 0]
dy = [0, -1, 0, 1]

pos = [0, 0]
xlist = [0]
ylist = [0]

dir = 0
L = list(input().rstrip())

for i in range(N):
    temp = L[i]
    match temp:
        case 'F':
            x, y = pos
            nx = x + dx[dir]
            ny = y + dy[dir]
            xlist.append(nx); ylist.append(ny)
            pos = [nx, ny]            
        case 'L':
            dir = (dir + 3) % 4
        case 'R':
            dir = (dir + 1) % 4

minx = min(xlist); maxx = max(xlist)
miny = min(ylist); maxy = max(ylist)

R, C = maxx - minx + 1, maxy - miny + 1
xf, yf = abs(minx), abs(miny)

maps = [['#'] * C for _ in range(R)]

for x, y in zip(xlist, ylist):
    maps[xf+x][yf+y] = '.'
for line in maps:
    print("".join(line))