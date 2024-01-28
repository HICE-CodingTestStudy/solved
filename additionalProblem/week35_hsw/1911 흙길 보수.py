import sys
input = sys.stdin.readline
n, l = map(int, input().split())
pools = [list(map(int, input().split())) for _ in range(n)]

pools.sort(key=lambda x:x[0])

cur, cnt = 0, 0 

for s, e in pools:
    if s > e:
        continue

    if cur > s:
        s = cur

    while s < e:
        s += l
        cnt += 1
    cur = start

print(cnt)
