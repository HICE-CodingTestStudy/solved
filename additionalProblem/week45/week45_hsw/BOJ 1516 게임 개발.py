# BOJ 1516 게임 개발
import sys
input = sys.stdin.readline
from collections import deque

if __name__ == '__main__':
    
    n = int(input())
    q = deque()
    graph = [[] for _ in range(n+1)]
    timelist = [0] * (n+1)
    degree = [0] * (n+1)
    time_sum = [0] * (n+1)
     
    for i in range(1, n+1):
        tmp = list(map(int, input().split()))
        timelist[i] = tmp[0]
        if tmp[1] != -1:
            for former in tmp[1:-1]:
                graph[former].append(i)
                degree[i] += 1
                
    for i in range(1, n+1):
        if degree[i] == 0:
            q.append(i)
            time_sum[i] = timelist[i]
    
    while q:
        current = q.popleft()
        
        for i in graph[current]:
            degree[i] -= 1
            time_sum[i] = max(time_sum[i], time_sum[current] + timelist[i])
            if degree[i] == 0:
                q.append(i)
    for i in time_sum:
        if i != 0:
            print(i)
    
    # buildings = {}    
    # timelist = [0]
    
    # for i in range(1, n+1):
    #     inp = list(map(int, input().split()))
    #     timelist.append(inp[0])
    #     if inp[1] == -1:
    #         continue
    #     else:
    #         required = inp[1:-1]
    #         for former in required:
    #             if i in buildings:
    #                 buildings[i].append(former)
    #             else:
    #                 buildings[i] = [former]
    # #print(f'timelist: {timelist}')
    # #print(f'buildings: {buildings}')
    
    # for i in range(1,n+1):
    #     if i in buildings:
    #         time = 0
    #         for former in buildings[i]:
    #             time = max(time, timelist[former])
    #         timelist[i] += time
            
    # for i in range(1, n+1):
    #     print(timelist[i])
    