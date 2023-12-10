#BOJ 2632
import sys
input = sys.stdin.readline
from collections import defaultdict
# defaultdict 사용법 
# https://emilkwak.github.io/defaultdict-rather-than-dict

pizzaSize = int(input())
pizzaA = []; pizzaB = []

m, n = map(int, input().split())
for i in range(m):
    pizzaA.append(int(input()))
for i in range(n):
    pizzaB.append(int(input()))

def searchCase(piz, L):
    case = defaultdict(int)
    for i in range(L):
        circled = piz[i:] + piz[:i] 
        total = 0
        for nums in circled:
            total += nums
            case[total] += 1
    case[sum(piz)] = 1
    return case

caseA = searchCase(pizzaA, m)
caseB = searchCase(pizzaB, n)

ans = caseA.get(pizzaSize, 0) + caseB.get(pizzaSize, 0)
for N in caseA:
    if pizzaSize-N in caseB:
        ans += caseA[N] * caseB[pizzaSize-N]
print(ans)