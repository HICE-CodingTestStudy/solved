#BOJ 2800

import sys
input = sys.stdin.readline
from itertools import combinations

L = list(input().rstrip())
stack = []; idx = []; result = []

for i, word in enumerate(L):
    if word == '(':
        stack.append(i)
    elif word == ')':
        idx.append((stack.pop(), i))
        
for i in range(1, len(idx)+1):
    # 여닫는 괄호 세트 위치를 조합으로 뽑음
    # ex) (0/(0)) -> (3,5) (0,6)
    C = list(combinations(idx, i))

    # 해당 조합 기준으로 괄호 삭제
    for comb in C:
        temp = list(L)
        for x, y in comb:
            temp[x] = ""
            temp[y] = ""
        result.append(''.join(temp))
# 중복 제거
result = set(result)

for i in sorted(result):
    print(i)
    
    