# BOJ 5567 결혼식
import sys
input = sys.stdin.readline

n = int(input())
m = int(input())
relation = [[] for _ in range(n+1)]
for i in range(m):
    a, b = map(int, input().split())
    relation[a].append(b)
    relation[b].append(a)

invitation = set([1])
for friend in relation[1]:
    invitation.add(friend)

    for ffriend in relation[friend]:
        invitation.add(ffriend)

print(len(invitation)-1)
