import sys
from collections import deque

input = sys.stdin.readline

N, K = map(int, input().split())
queue = deque()
answer = []
for i in range(1, N + 1):
    queue.append(str(i))

for i in range(N):
    cnt = 0
    while cnt < K - 1:
        tmp = queue.popleft()
        queue.append(tmp)
        cnt += 1

    answer.append(queue.popleft())

output = "<" + ", ".join(answer) + ">"
print(output)
