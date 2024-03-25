# BOJ 20058 마법사 상어와 파이어스톰
import sys
input = sys.stdin.readline
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

n, q = map(int, input().split())
length = 2**n

a = []
for _ in range(length):
    a.append(list(map(int, input().split())))

L_list = list(map(int, input().split()))



# 칸 회전 구현
def rotate(sx, sy, L):
    temp = deque()
    for x in range(sx, sx+(2**L)):
        for y in range(sy, sy+(2**L)):
            temp.append(a[x][y])
    for y in reversed(range(sy, sy+(2**L))):
        for x in range(sx, sx+(2**L)):
            a[x][y] = temp.popleft()


# 얼음칸 확인 및 감소
def check_ice():
    tempa = [[0] * length for _ in range(length)]
    for i in range(length):
        for j in range(length):
            if a[i][j]:
                check_near(i, j, tempa)
    for i in range(length):
        for j in range(length):
            a[i][j] += tempa[i][j]

# 얼음칸 인접 확인 및 감소
def check_near(sx, sy, tempa):
    ice_cnt = 0
    for i in range(4):
        nx, ny = sx+dx[i], sy+dy[i]
        if 0 <= nx < length and 0 <= ny < length:
            if a[nx][ny]:
                ice_cnt += 1
    if ice_cnt < 3:
        tempa[sx][sy] -= 1

# 남아있는 얼음 A[r][c]의 합
def sum_rest():
    cnt = 0
    for i in range(length):
        for j in range(length):
            cnt += a[i][j]
    print(cnt)

# 가장 큰 얼음덩어리 확인
def check_iceberg():
    visited = [[False] * length for _ in range(length)]
    max_iceberg = 0
    for i in range(length):
        for j in range(length):
            if a[i][j] and not visited[i][j]:
                temp = bfs(i, j, visited)
                max_iceberg = max(max_iceberg, temp)
    print(max_iceberg)

def bfs(sx, sy, visited):
    q = deque()
    q.append((sx, sy))
    ice_cnt = 0
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < length and 0 <= ny < length:
                if a[nx][ny] and not visited[nx][ny]:
                    visited[nx][ny] = True
                    ice_cnt += 1
                    q.append((nx, ny))
    return ice_cnt

'''
def p(a):
    for i in range(length):
        for j in range(length):
            print(a[i][j], end = ' ')
        print()
'''

# main
if __name__ == '__main__':
    for L in L_list:
        #print('before rotate, L is', L, '\n')
        #p(a)
        for i in range(0, 2**n, 2**L):
            for j in range(0, 2**n, 2**L):
                if 0<=i<length and 0<=j<length:
                    rotate(i,j, L)
        #print('after rotate\n')
        #p(a)
        #print()
        #print('before check_ice\n')
        #p(a)
        check_ice()
        #print('after check_ice\n')
        #p(a)
        #print()
    sum_rest()
    check_iceberg()
