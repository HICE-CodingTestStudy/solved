# 프로그래머스 '미로 탈출 명령어'
# 2023 KAKAO BLIND RECRUITMENT
import sys
sys.setrecursionlimit(10**8)
dx = [1, 0,  0, -1]
dy = [0, -1, 1, 0]
dir = ['d', 'l', 'r', 'u']

def solution(n, m, x, y, r, c, k):
    def dfs(x, y, directions, depth):
        global answer
        if k < depth + abs(x-r) + abs(y-c):
            return
        if x == r and y == c and depth == k:
            return directions
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 1<=nx<=n and 1<=ny<=m:
                result = dfs(nx, ny, directions + dir[i], depth+1)
                if result:
                    return result
        return
                    
    distance = abs(x-r) + abs(y-c)
    if distance > k or (k-distance) % 2 == 1:
        return "impossible"
    
    answer = dfs(x, y, '', 0)
    return "impossible" if not answer else answer

print(solution(3, 4, 2, 3, 3, 1, 5))