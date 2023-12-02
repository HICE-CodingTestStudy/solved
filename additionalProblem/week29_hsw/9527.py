import sys
input = sys.stdin.readline
from collections import deque

def binary(x):
    if x < 2:
        return x
    
    q = deque()
    x1 = x
    while x1 > 0:
        q.append(x1 % 2)
        x1 //= 2

    C = len(q) - 1

    return 2**(C - 1) * C + 1 + (x - 2**C) + binary(x - 2**C)

A, B = map(int, input().split())

ans = binary(B) - binary(A - 1)
print(ans)
