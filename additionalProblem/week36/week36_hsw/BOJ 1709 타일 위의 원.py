#BOJ 1709 타일 위의 원
import sys
input = sys.stdin.readline

n = int(input())
x, y, r = 0, n//2-1, (n//2)**2
cnt = 0

def distance(x, y):
    return x*x + y*y

while x <= n//2 and y >= 0:
    d = distance(x+1, y)
    if d <= r:
        x += 1
    if d >= r:
        y -= 1
    cnt += 1
    
print(4*cnt)
