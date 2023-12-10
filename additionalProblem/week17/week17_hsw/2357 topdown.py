#BOJ 2357

import sys
input = sys.stdin.readline
import math

N, M = map(int, input().split())
nums = list(int(input()) for _ in range(N))

"""
# 기본 세그먼트 트리 구조, Top-down
def segment(l, r, i):
    if l == r:
        segmentTree[i] = nums[l]
        return segmentTree[i]
    mid = (l+r)//2
    segmentTree[i] = segment(l, mid, i * 2) + segment(mid + 1, r, i * 2 + 1)
    return segmentTree[i]
"""

minTree = [0] * 2**(int(math.ceil(math.log(N, 2)+1)))
maxTree = [0] * 2**(int(math.ceil(math.log(N, 2)+1)))

def segMin(l, r, i):
    if l == r:
        minTree[i] = nums[l]
        return minTree[i]
    mid = (l+r)//2
    minTree[i] = min(segMin(l, mid, i * 2), segMin(mid + 1, r, i * 2 + 1))
    return minTree[i]

def segMax(l, r, i):
    if l == r:
        maxTree[i] = nums[l]
        return maxTree[i]
    mid = (l+r)//2
    maxTree[i] = max(segMax(l, mid, i * 2), segMax(mid + 1, r, i * 2 + 1))
    return maxTree[i]

segMin(0, N-1, 1)
segMax(0, N-1, 1)

def minQuery(l, r, i, lnode, rnode):
    if lnode > r or rnode < l:
        return math.inf
    if lnode <= l and r <= rnode:
        return minTree[i]
    mid = (l+r)//2
    return min(minQuery(l, mid, i * 2, lnode, rnode), minQuery(mid+1, r, i * 2 + 1, lnode, rnode))

def maxQuery(l, r, i, lnode, rnode):
    if lnode > r or rnode < l:
        return -1
    if lnode <= l and r <= rnode:
        return maxTree[i]
    mid = (l+r)//2
    return max(maxQuery(l, mid, i * 2, lnode, rnode), maxQuery(mid+1, r, i * 2 + 1, lnode, rnode))

for i in range(M):
    l, r = map(int, input().split())
    print(minQuery(0, N-1, 1, l-1, r-1), maxQuery(0, N-1, 1, l-1, r-1))