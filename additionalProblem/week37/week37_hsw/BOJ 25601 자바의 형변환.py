#BOJ 25601 자바의 형변환
import sys
input = sys.stdin.readline

def check(maps, child, info):
    parent = child
    while parent in maps:
        parent = maps[parent]
        if parent == info:
            return 1
    return 0

n = int(input())
maps = {}
for i in range(n-1):
    a, b = map(str, input().split())
    maps[a] = b

a, b = input().split()

print(check(maps, a, b) or check(maps, b, a))
