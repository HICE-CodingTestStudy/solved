import sys


def dfs(num, parent):
    parent[num] = -2
    for i in range(len(parent)):
        if num == parent[i]:  # num이 i의 parent면 i도 같이 삭제
            dfs(i, parent)


N = int(input())
parent = list(map(int, sys.stdin.readline().split()))
K = int(input())
count = 0

dfs(K, parent)
count = 0
# print(parent)
for i in range(len(parent)):
    if parent[i] != -2 and i not in parent:
        count += 1
print(count)
