#BOJ 11578
import sys
input = sys.stdin.readline
from itertools import combinations

#N : 문제 수 M: 학생 수
N, M = map(int,input().split())

solved = []

for i in range(M):
    sol = 0b0000000000
    temp = list(map(int,input().split()))
    Qnum, Ps = temp[0], temp[1:]
    for problem in Ps:
        #비트마스킹 원소 추가
        sol |= (1 << problem)
    solved.append(sol)

ans = -1
for cnt in range(M+1):
    cases = list(combinations(solved, cnt)) 
    flag = False
    for case in cases:
        sol = 0b0000000000
        for c in case:
            sol |= c
        # sum nC0, nC1, nC2, nC3, ..., nCn = 2**n
        if sol == 2**(N+1) - 2:
            ans = cnt; flag = True
            break
    if flag:
        break
print(ans)
