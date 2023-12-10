#BOJ 20529
import sys
input = sys.stdin.readline
from itertools import combinations

def mbti_dist(a, b):
    dist = 0
    for i, j in zip(a, b):
        if i != j:
            dist += 1
    return dist

T = int(input())
for _ in range(T):
    N = int(input())
    MBTI = input().rstrip().split()

    if N > 32: #16/16/1 -> 비둘기집
        print(0)
    else:
        ans = 13    # 세 개인의 최대 심리거리 + 1
        case = combinations(MBTI, 3)    # 세 명을 뽑을 조합
        for a, b, c in case:
            dist = 0
            dist += mbti_dist(a, b)
            dist += mbti_dist(b, c)
            dist += mbti_dist(a, c)

            ans = min(ans, dist)
        print(ans)