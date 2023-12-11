#BOJ 9205
import sys
input = sys.stdin.readline
from collections import deque

#50m 마다 한병, 20개 -> 1000m

T = int(input())
for _ in range(T):
    N = int(input())
    xhouse,yhouse = map(int,input().split())
    conv = []
    for i in range(N):
        xconv, yconv = map(int, input().split())
        conv.append((xconv, yconv))
    xpent, ypent = map(int, input().split())
    visited = [False] * (N+1)

    flag = False
    q = deque()
    q.append((xhouse,yhouse))
    while q:
        x, y = q.popleft()
        if abs(x-xpent) + abs(y-ypent)<=1000:
            flag = True
            continue
        for i in range(N):
            if not visited[i]:
                tempx, tempy = conv[i]
                if abs(x-tempx) + abs(y-tempy) <= 1000:
                    visited[i] = True
                    q.append((tempx, tempy))

    print('happy') if flag else print('sad')

            
        