# BOJ 14712 넴모넴모
import sys
input = sys.stdin.readline

N, M = map(int, input().split())

board = [[0] * (M + 1) for _ in range(N + 1)]

ans = 0

def dfs(depth):
    global ans
    
    # 모든 격자를 확인했을 때, 조건을 만족하는 경우를 찾았으므로 ans를 증가시키고 반환.
    if depth == N * M:
        ans += 1
        return
    
    # 현재 위치 계산
    x = depth // M + 1
    y = depth % M + 1

    # 현재 위치주변에 0이 있으면 해당 위치를 1로 채우고,
    # 다음 단계의 DFS를 호출
    if board[x - 1][y] == 0 or board[x - 1][y - 1] == 0 or board[x][y - 1] == 0:
        board[x][y] = 1
        dfs(depth + 1)
        
        board[x][y] = 0  # 백트래킹: 현재 위치를 0으로 되돌림.
    
    # 현재 위치를 1로 채우지 않고 다음 단계의 DFS를 호출.
    dfs(depth + 1)

dfs(0)

print(ans)
