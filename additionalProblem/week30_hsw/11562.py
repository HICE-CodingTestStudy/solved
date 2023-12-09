# BOJ 11562

import sys
input = sys.stdin.readline

n, m = map(int, input().split())

route = [[1e9] * (n+1) for _ in range(n+1)]

# 간선 정보 입력 및 route 배열 초기화
for _ in range(m):
    u, v, c = map(int, input().split())
    if c:  # c가 1이면 양방향으로 갈 수 있는 간선
        route[u][v] = 0
        route[v][u] = 0
    else:  # c가 0이면 단방향으로만 갈 수 있는 간선
        route[u][v] = 0
        route[v][u] = 1

# 플로이드-워셜
for k in range(1, n+1):
    for i in range(1, n+1):
        for j in range(1, n+1):
            if route[i][j] > route[i][k] + route[k][j]:
                route[i][j] = route[i][k] + route[k][j]

# 자기 자신으로의 최단 경로는 항상 0으로 설정
for i in range(n+1):
    route[i][i] = 0

k = int(input())
for _ in range(k):
    start, end = map(int, input().split())
    print(route[start][end])
