# BOJ 1029 그림 교환
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    graph = [list(map(int, list(input().strip()))) for _ in range(n)]
    memo = {}
    
    def dfs(cur, visited, price):
        if(cur, visited, price) in memo:
            return memo[(cur, visited, price)]
        
        ans = 1
        for next in range(n):
            if not (visited & (1 << next)):
                next_price  = graph[cur][next]
                if next_price >= price:
                    ans = max(ans, 1 + dfs(next, visited | (1 << next), next_price))
                    
        memo[(cur, visited, price)] = ans
        return ans
    
    print(dfs(0,1,0))
