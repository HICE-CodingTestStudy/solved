#boj 15723
import sys
input = sys.stdin.readline

N = int(input())

area = [[1e9] * 26 for _ in range(26)]

for _ in range(N):
    x, y = map(ord, input().rstrip().split(" is "))
    area[x-ord('a')][y-ord('a')] = 1

for k in range(26):
    for i in range(26):
        for j in range(26):
            area[i][j] = min(area[i][j], area[i][k]+area[k][j])


M = int(input())
for i in range(M):
    x, y = map(ord, input().rstrip().split(" is "))
    if area[x-ord('a')][y-ord('a')] < 1e9:
        print('T')
    else:
        print('F')