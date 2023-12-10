#BOJ 20057

import sys
input = sys.stdin.readline

N, M = map(int, input().split())
maps = [list(input().rstrip()) for _ in range(N)]
visited = [[False] * M for _ in range(N)]
dic = {'D': [0, 1], 'L' : [-1, 0], 'R': [1,0], 'U': [0, -1]}
parent = [i for i in range(N*M)]

def getParent(x):
    if x==parent[x]:
        return x
    return getParent(parent[x])
    
def unionParent(a,b):
    a = getParent(a)
    b = getParent(b)
    if a == b:
        return True
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

for num in range(N*M):
    x = num % M; y = num // M
    cur = maps[y][x]
    nx = x+dic[cur][0];  ny = y+dic[cur][1]    
    next = ny * M + nx
    if next < 0 or next >= N * M:
        continue
    unionParent(num, next)
    
ans = 0
visited = {}
for i in range(N*M):
    if getParent(parent[i]) not in visited:
        ans+=1
        visited[parent[i]] = True
print(ans)



        