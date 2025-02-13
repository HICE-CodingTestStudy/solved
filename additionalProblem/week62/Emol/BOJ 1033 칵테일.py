import sys, math
from collections import deque
from fractions import Fraction

input = sys.stdin.readline

def lcm(a, b):
    return a * b // math.gcd(a, b)

if __name__ == "__main__":
    n = int(input().strip())
    
    if n == 1:
        print(1)
    else:
        graph = [[] for _ in range(n)]
        
        for _ in range(n-1):
            a, b, p, q = map(int, input().split())
            graph[a].append((b, p, q))
            graph[b].append((a, q, p))
    
        masses = [None] * n
        masses[0] = Fraction(1, 1)
        

        queue = deque([0])
        
        while queue:
            cur = queue.popleft()
            for nxt, p, q in graph[cur]: 
                if masses[nxt] is None:
                    masses[nxt] = masses[cur] * Fraction(q, p)
                    queue.append(nxt)
        
        common = 1
        for m in masses:
            common = lcm(common, m.denominator)
            
        int_masses = [int(m * common) for m in masses]
        
        g = int_masses[0]
        for m in int_masses[1:]:
            g = math.gcd(g, m)
        int_masses = [m // g for m in int_masses]
        
        print(" ".join(map(str, int_masses)))
