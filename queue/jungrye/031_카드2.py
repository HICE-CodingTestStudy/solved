from collections import deque

n = int(input())

que = deque()
for i in range(1, n+1):
    que.append(i)

while len(que) > 1:
    que.popleft()
    if len(que) == 1:
        break
    que.append(que.popleft())
print(que.pop())
