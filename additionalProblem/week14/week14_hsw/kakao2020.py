import sys
input = sys.stdin.readline
from collections import deque

def solution(board):
    
    answer = float('inf')
    N = len(board)
    q = deque()
    
    for start in [(0,0,0,0), (0,0,2,0)]:    # 우, 하 start
        
        nboard = [[float('inf') for i in range(N)] for j in range(N)]
        
        q.appendleft(start)
        
        while q:
            x, y, prev, cost = q.popleft()
            
            for idx, (dx, dy) in enumerate([(0, 1), (0, -1), (1, 0), (-1, 0)]):   # 우, 좌, 하, 상
                
                nx, ny = dx + x, dy + y
                if idx != prev:
                    ncost = cost + 600
                else:
                    ncost = cost + 100
                
                if 0 <= nx < N and 0 <= ny < N and nboard[nx][ny] > ncost and board[nx][ny] == 0:
                    q.append([nx, ny, idx, ncost])
                    nboard[nx][ny] = ncost
                    
        answer = min(answer, nboard[-1][-1])
    return answer
