#BOJ 16507
import sys
input = sys.stdin.readline
from collections import deque

# 1000 * 1000 * 10000 -> 10억번 계산. 단순 계산 불가
# 2차원 부분합 활용 https://yiyj1030.tistory.com/489

R, C, Q = map(int, input().split())

arr = [list(map(int, list(input().split(' ')))) for _ in range(R)]

# arr 원소의 합을 저장하는 배열 sum_arr
sum_arr = [[0 for _ in range(C+1)] for _ in range(R+1)]
for i in range(1, R+1):
    for j in range(1, C+1):
        sum_arr[i][j] = arr[i-1][j-1] + sum_arr[i-1][j] + sum_arr[i][j-1] - sum_arr[i-1][j-1]

dots = deque()
for _ in range(Q):
    r1, c1, r2, c2 = map(int, input().split())
    r1 -= 1
    c1 -= 1
    r2 -= 1
    c2 -= 1
    dots.append((r1, c1))
    dots.append((r2, c2))
    while dots:
        r1, c1 = dots.popleft()
        r2, c2 = dots.popleft()
        rL, cL = r2-r1+1, c2-c1+1
        
        light = 0
        S = sum_arr[r2+1][c2+1] - sum_arr[r1][c2+1] - sum_arr[r2+1][c1] + sum_arr[r1][c1]
        light = S//(rL*cL)
        print(light)