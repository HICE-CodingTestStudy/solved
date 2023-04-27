from queue import PriorityQueue

que = PriorityQueue()

n = int(input())
dasom = int(input())
count = 0

if n == 1:
    print(0)
else:
    for i in range(n-1):
        que.put(-int(input()))
    temp = -que.get()
    while dasom <= temp:
        temp -= 1
        dasom += 1
        count += 1
        que.put(-temp)
        temp = -que.get()
    print(count)
