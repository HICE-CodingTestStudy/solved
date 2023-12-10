#boj 1700
import sys
input = sys.stdin.readline
from collections import deque

N, K = map(int, input().split())
q = deque()
order = list(map(int, input().split()))

cnt = 0

for i in range(K):
    last = 0
    temp = 0

    if order[i] in q:
        continue

    if order[i] not in q and len(q) != N:
        q.append(order[i])
        continue
    
    #빈도 확인 후 제거
    for device in q:
        if device not in order[i:]:
            temp = device
            break
        elif order[i:].index(device) > last:
            last = order[i:].index(device)
            temp = device
    q[q.index(temp)] = order[i]        
    cnt += 1

print(cnt)
        
        