# BOJ 1183 약속

import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input().strip())
    d = []
    for _ in range(n):
        A, B = map(int, input().split())
        d.append(B - A)
        
    d.sort()
    
    if n % 2 == 1:
        print(1)
    else:
        left = d[n // 2 - 1]
        right = d[n // 2]
        print(right - left + 1)
        
