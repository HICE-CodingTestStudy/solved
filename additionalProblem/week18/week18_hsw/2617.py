#BOJ 2617

import sys
input = sys.stdin.readline

N, M = map(int, input().split())

balls = [[0]*(N+1) for _ in range(N+1)]

# (가벼운것, 무거운것)으로 관계 표현
for i in range(M):
    l, h = map(int, input().split())
    balls[l][h] = 1

# 연속된 것 갱신
for k in range(1, N+1):
    for i in range(1, N+1):
        for j in range(1, N+1):
            if balls[i][k] and balls[k][j]:
                balls[i][j] = 1

ans = 0

for i in range(1, N+1):
    left_cnt = 0; right_cnt = 0
    for j in range(1, N+1):
        if i == j:
            continue
        elif balls[i][j] == 1: # 현재 구슬보다 가벼운 구슬 갯수
            right_cnt += 1
        elif balls[j][i] == 1: # 현재 구슬보다 무거운 구슬 갯수
            left_cnt += 1
    if right_cnt > N//2 or left_cnt > N//2: # 중간값 보다 많으면 중간구슬 X
        ans += 1
print(ans)