import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N)]
n_hackable = [0 for _ in range(N)]


def parse_input():
    for _ in range(M):
        A, B = map(int, input().split())
        graph[A - 1].append(B - 1)


def print_answer(max):
    for n in range(N):
        if n_hackable[n] == max:
            print((n + 1) + ' ')


def count_hackable(start):
    queue = deque()
    visited = [[False] * N for _ in range(N)]

    queue.append(start)
    visited[start] = True

    while queue:
        n = queue.popleft()
        if not visited[n]:
            queue.append(n)
            visited[n] = True
            n_hackable[n] += 1


def solution():
    for n in range(N):
        count_hackable(n)

    max = -1
    for n in range(N):
        if n_hackable[n] > max:
            max = n_hackable[n]
    
    return max

parse_input()
max = solution()
print_answer(max)
