#BOJ 1174 줄어드는 수
import sys
input = sys.stdin.readline
from itertools import combinations

n = int(input())
result = set()

# 감소하는 수 모든 경우 생성
# 0~9까지의 숫자로 1~10개의 조합
for i in range(1, 11):
    for comb in combinations(range(0, 10), i):
        comb = sorted(comb, reverse=True)
        result.add(int(''.join(map(str, comb))))
result = sorted(result)
try:
    print(result[n - 1])
except IndexError:
    print(-1)
