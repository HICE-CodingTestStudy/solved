#boj 15671
import sys
input = sys.stdin.readline

area = [["."] * 7 for _ in range(7)]
area[3][3]='W'; area[3][4] = 'B'; area[4][3] = 'B'; area[4][4] = 'W'
visited = [[False] * 7 for _ in range(7)]

dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

def change(type):
    for i in range(1,7):
        for j in range(1,7):
            if(type % 2 == 1 and visited[i][j]): 
                area[i][j] = 'B'
            elif(type % 2 == 0 and visited[i][j]): 
                area[i][j] = 'W'

def check(x, y, type, dir):
    flag = False
    nx = x; ny = y
    visited[nx][ny] = True
    while(True):
        nx += dx[dir]; ny += dy[dir]
        if(nx < 0 or nx >= 7 or ny < 0 or ny >=7):
            break
        if(visited[nx][ny] or area[nx][ny] == '.'):
            break
        if(area[nx][ny] == 'B' and type % 2 == 1):
            flag = True
            break
        if(area[nx][ny] == 'W' and type % 2 == 0):
            flag = True
            break
        visited[nx][ny] = True

    return flag

if __name__ == '__main__':
    N = int(input())

    for i in range(1, N+1):
        R, C = map(int, input().split())
        for j in range(8):
            visited = [[False] * 7 for _ in range(7)]
            if(check(R, C, i, j)):
                change(i)

    wSum, bSum = 0, 0
    for i in range(1,7):
        for j in range(1,7):
            if(area[i][j] == 'W'): 
                wSum += 1
            elif(area[i][j] == 'B'): 
                bSum += 1
            print(area[i][j], end='')
        print()

    print("White") if wSum > bSum else print("Black")






