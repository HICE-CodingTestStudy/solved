#BOJ 1325
import sys
input = sys.stdin.readline
from collections import deque

def find_parent(parent, x):
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b
        
# 컴퓨터의 수 N, 신뢰 관계의 수 M 입력
N, M = map(int, input().split())

# 부모 테이블 초기화
parent = [i for i in range(N+1)]

# 신뢰 관계 입력 및 union 연산 수행
for _ in range(M):
    A, B = map(int, input().split())
    union_parent(parent, A, B)

    # 각 컴퓨터의 부모 노드 찾기
    for i in range(1, N+1):
        find_parent(parent, i)

    # 각 컴퓨터의 부모 노드 개수 세기
    counts = [0] * (N+1)
    for i in range(1, N+1):
        counts[find_parent(parent, i)] += 1

    # 가장 많이 해킹할 수 있는 컴퓨터의 수 찾기
    max_hack = max(counts)

    # 결과 출력
    for i in range(1, N+1):
        if counts[i] == max_hack:
            print(i, end=' ')
