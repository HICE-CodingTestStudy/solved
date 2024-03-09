# BOJ 1956 운동
import sys
input = sys.stdin.readline
INF = int(1e9)
v, e = map(int, input().split())
maps = [[INF] * (v+1) for _ in range(v+1)]
for _ in range(e):
    a, b, c = map(int, input().split())
    maps[a][b] = c

for k in range(1, v+1):
    for i in range(1, v+1):
        for j in range(1, v+1):
            if maps[i][k] + maps[k][j] < maps[i][j]:
                maps[i][j] = maps[i][k] + maps[k][j]

ans = INF
for i in range(1, v+1):
    ans = min(ans, maps[i][i])

print(ans) if ans != INF else print(-1)


'''
# 다익스트라 알고리즘 사용
import heapq
import sys
input = sys.stdin.readline
INF = int(1e9)
v, e = map(int, input().split())
maps = [[INF] * (v+1) for _ in range(v+1)]

q = []
for _ in range(e):
    a, b, c = map(int, input().split())
    maps[a][b] = c
    heapq.heappush(q, (c, a, b))

while q:
    dist, start, end = heapq.heappop(q)
    if start == end:
        print(dist)
        break
    if maps[start][end] < dist:
        continue

    for next_dist, next_end in maps[end]:
        new_dist = dist + next_dist
        if new_dist < maps[start][next_end]:
            maps[start][next_end] = new_dist
            heapq.heappush(q, (new_dist, start, next_end))
else:
    print(-1)
'''
