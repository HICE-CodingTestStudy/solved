#BOJ 19535 ㄷㄷㄷㅈ
import sys
input = sys.stdin.readline

n = int(input())
edges = []
degree = [ 0 for _ in range(n+1)]
for _ in range(n-1):
    u, v = map(int, input().split())
    edges.append((u, v))
    degree[u] += 1
    degree[v] += 1
    
D, G = 0, 0

# ㄷ 트리 카운트
for a, b in edges:
    D += (degree[a] - 1) * (degree[b] - 1)

# ㅈ 트리 카운트
for i in range(1, n+1):
    G += (degree[i] * (degree[i] - 1) * (degree[i] - 2)) // 6
        
if D > 3* G:
    print("D")
elif D < 3* G:
    print("G")
else:
    print("DUDUDUNGA")