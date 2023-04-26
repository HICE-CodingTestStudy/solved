import sys
from queue import PriorityQueue

input = sys.stdin.readline
queue = PriorityQueue()

N = int(input())  # 후보의 수
dasom = 0
for i in range(N):
    if i == 0:
        dasom = int(input())
    else:
        queue.put(-int(input()))

if N == 1:
    print(0)
    exit()

cnt = 0
while True:
    tmp = -queue.get()
    if dasom <= tmp:
        tmp -= 1
        dasom += 1
        cnt += 1
        queue.put(-tmp)

    else:
        break

print(cnt)
