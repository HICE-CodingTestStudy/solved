#BOJ 16928
import sys
input = sys.stdin.readline
from collections import deque

maps = [False] * 101
visited = maps
ups = {}; downs = {}

N, M = map(int, input().split())
for _ in range(N):
    x, y = map(int, input().split())
    ups[x] = y
for _ in range(M):
    u, v = map(int, input().split())
    downs[u] = v

q = deque([1])
while q:
    pos = q.popleft()
    if pos == 100:
        print(maps[pos])
        break
    for dice in range(1,7):
        npos = pos + dice
        if npos <= 100 and not visited[npos]:
            if npos in ups.keys():
                npos = ups[npos]
            if npos in downs.keys():
                npos = downs[npos]
            if not visited[npos]:
                visited[npos] = True
                maps[npos] = maps[pos] + 1
                q.append(npos)
    
        
