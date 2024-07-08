# BOJ 21606 아침산책
import sys
from collections import defaultdict, deque

input = sys.stdin.readline


def BFS(s):
    queue = deque([s])
    visited.add(s)
    indoor = 0

    while queue:
        v = queue.popleft()
        for i in tree[v]:
            if a[i - 1] == 0:
                if i not in visited:
                    visited.add(i)
                    queue.append(i)
            else:
                indoor += 1

    return indoor


n = int(input())
a = list(map(int, list(input().strip())))
tree = defaultdict(list)
ans = 0

for _ in range(n - 1):
    u, v = map(int, input().split())
    tree[u].append(v)
    tree[v].append(u)
    if a[u - 1] == 1 and a[v - 1] == 1:
        ans += 2

visited = set()
for i in range(1, n + 1):
    indoor = 0
    if a[i - 1] == 0:
        if i not in visited:
            indoor = BFS(i)
    ans += indoor * (indoor - 1)

print(ans)
