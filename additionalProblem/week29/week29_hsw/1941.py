#BOJ 1941
import sys
input = sys.stdin.readline
from collections import deque

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def bfs(a,b):
    q = deque()
    vvisited = [[0]*5 for _ in range(5)]

    q.append((a,b))
    vvisited[a][b]=1
    cnt = 1

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0<=nx<5 and 0<=ny<5 and vvisited[nx][ny]==0 and visited[nx][ny]==1:
                q.append((nx,ny))
                vvisited[nx][ny]=1
                cnt+=1
    return cnt==7

def check():
    for i in range(5):
        for j in range(5):
            if visited[i][j]==1:
                return bfs(i,j)

def dfs(n, cnt, scnt):
    global ans
    if cnt>7:     
        return

    if n == 25:
        if cnt==7 and scnt>=4: 
            if check():  
                ans+=1
        return

    visited[n//5][n%5]=1      
    dfs(n+1, cnt+1, scnt+int(arr[n//5][n%5]=='S'))
    visited[n//5][n%5]=0   
    dfs(n+1, cnt, scnt) 

arr = [input() for _ in range(5)]
ans = 0
visited = [[0]*5 for _ in range(5)]
dfs(0, 0, 0)
            
        
print(ans)
