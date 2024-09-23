# BOJ 12869 뮤탈리스크
# dp, dfs
import sys

input = sys.stdin.readline

attack_patterns = [
    (9, 3, 1),
    (9, 1, 3),
    (3, 9, 1),
    (3, 1, 9),
    (1, 9, 3),
    (1, 3, 9)
]

def sol(a, b, c):
    if a == 0 and b == 0 and c == 0:
        return 0
    if dp[a][b][c]:
        return dp[a][b][c]
    
    dp[a][b][c] = int(1e9)
    
    for attack in attack_patterns:
        na = max(0, a-attack[0])
        nb = max(0, b-attack[1])
        nc = max(0, c-attack[2])
        
        dp[a][b][c] = min(dp[a][b][c], sol(na, nb, nc)+1)

    return dp[a][b][c]
    
    

if __name__ == "__main__":
    n = int(input())
    scvs = list(map(int, input().split()))
    
    # dp : a,b,c의 체력을 가진 SCV 체력을 0으로 만드는 최소횟수
    dp = [[[0] * 61 for _ in range(61)] for _ in range(61)]
    
    scvs.extend([0,0])
    
    result  = sol(scvs[0],scvs[1], scvs[2])
    
    print(result)
    
    
    
        
        
    
    
    