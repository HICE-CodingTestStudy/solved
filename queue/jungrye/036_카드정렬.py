import sys
from queue import PriorityQueue

queue = PriorityQueue()
n = int(sys.stdin.readline().strip())
for i in range(n):
    queue.put(int(sys.stdin.readline().strip()))


if n == 1:
    print(0)
else:
    sum = 0
    while True:
        if queue.qsize() == 1:
            break
        else:
            tempSum = queue.get_nowait()
            get = queue.get_nowait()
            tempSum += get
            sum += tempSum
            queue.put_nowait(tempSum)

    print(sum)
