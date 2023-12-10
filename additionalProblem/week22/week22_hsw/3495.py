#BOJ 3495
import sys
input = sys.stdin.readline
h, w = map(int, input().split())
maps = [list(input()) for _ in range(h)]
area = 0
for i in range(h):
    L = 0
    for j in maps[i]:
        if j == '/' or j == '\\':
            area += 0.5; L += 1
        else:
            if L%2:
                area += 1
print(int(area))