import sys
from collections import deque
# import queue -> 시간초과 불가피

queue = deque()
myList = []
n, k = map(int, sys.stdin.readline().split())
for i in range(1, n+1):
    queue.append(i)

num = k-1
while len(queue) != 0:
    if num == 0:
        myList.append(queue.popleft())
        num = k-1
    else:
        queue.append(queue.popleft())
        num -= 1

print("<", end='')
for i in range(len(myList)):
    if i == len(myList)-1:
        print(myList[i], end='>')
    else:
        print(myList[i], end=', ')

# queue       num   pop   출력
# 12(3)4567   2     1
# 2(3)45671   1     2
# (3)456712   0     3     3
# 45(6)712    2     4
# 5(6)7124    1     5
# (6)71245    0     6     6
# ...
