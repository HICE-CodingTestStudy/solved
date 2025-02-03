# BOJ 1106 호텔
import sys

input = sys.stdin.readline

INF = 1e9

if __name__ == "__main__":
    c, n = map(int, input().split())
    cities = []
    max_customers = 0
    for _ in range(n):
        cost, customers = map(int, input().split())
        cities.append((cost, customers))
        max_customers = max(max_customers, customers)
        
    max_need = c + max_customers
    dp = [INF] * (max_need+1)
    dp[0] = 0
    
    for i in range(max_need + 1):
        for cost, customers in cities:
            if i + customers <= max_need:
                dp[i+customers] = min(dp[i+customers], dp[i] + cost)
    print(min(dp[c:]))