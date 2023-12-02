#BOJ 21600
import sys
input = sys.stdin.readline

N = int(input())
L = list(map(int, input().split()))
ans = 0
tmp = 0
for x in L:
    temp = temp + 1 if temp+1 <= x else x
    ans = max(ans, temp)

print(ans)
    
