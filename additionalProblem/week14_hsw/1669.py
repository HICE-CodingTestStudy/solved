#boj 1669
import sys
input = sys.stdin.readline

X, Y = map(int, input().split())

d = Y-X

if d<1:
    print(d)
    
else:
    N=0
    
    while N*N < d:
        N += 1
        
    if N * N != d:
        N -= 1
        
    result = 2*N-1
    
    d -= N*N

    while d>0:
        d -= min(N, d)
        result += 1
        
    print(result)