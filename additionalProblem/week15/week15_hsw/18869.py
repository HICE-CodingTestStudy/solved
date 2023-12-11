#boj 18869
import sys
input = sys.stdin.readline

M, N = map(int, input().split())
d = {}
for _ in range(M):
    space = list(map(int, input().split()))

    space_sort = sorted(list(set(space)))

    rank = {space_sort[i]:i for i in range(len(space_sort))}

    order = str(rank[j] for j in space)

    d[order] = d.get(order, 0) + 1

print(sum(map(lambda x : x*(x-1)//2, d.values())))