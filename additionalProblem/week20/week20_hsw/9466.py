#BOJ 9466

import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    N = int(input())
    nums = [0] + list(map(int, input().split()))
    visited = [False] * (N+1)

    ans = 0
    
    for i in range(1, N+1):
        if not visited[i]:
            # 일단 끝까지 따라감
            start = i
            while not visited[start]:
                visited[start] = 1
                start = nums[start]
                #루프 형성된걸 찾으면, 루프 탈출
            # 시작점부터 사이클을 못만든 애들 카운트
            temp2 = i
            while temp2 != start:
                ans += 1
                temp2 = nums[temp2]

    print(ans)
                
