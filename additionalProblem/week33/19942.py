# BOJ 19942
import sys
input = sys.stdin.readline
from itertools import combinations

MAX = int(1e9)
N = int(input())
mp, mf, ms, mv = map(int, input().split())
food = {}
for i in range(N):
    pi, fi, si, vi, ci = map(int,input().split())
    food[i+1] = (pi, fi, si, vi, ci)

def sol():
    mincost = MAX
    mincomb = None
    for i in range(1, N+1):
        for comb in combinations(range(1, N+1), i):
            pt, ft, st, vt, ct = 0, 0, 0, 0, 0
            for element in comb:
                pt += food[element][0]
                ft += food[element][1]
                st += food[element][2]
                vt += food[element][3]
                ct += food[element][4]

            if pt >= mp and ft >= mf and st >= ms and vt >= mv:
                if mincost > ct:
                    mincost = ct
                    mincomb = comb
                # 같은 비용의 집합이 하나 이상일경우
                elif mincost == ct:
                    mincomb = sorted((mincomb, comb))[0]
    if mincost == MAX:
        print(-1)
    else:
        print(mincost)
        print(" ".join(map(str,mincomb)))

sol()