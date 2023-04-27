from collections import deque
from queue import PriorityQueue


def solution(priorities, location):
    answer = 0
    queue = deque()
    loca = deque()
    pQueue = PriorityQueue()

    for i in priorities:
        queue.append(i)
        pQueue.put(-i)
    for i in range(len(priorities)):
        if i == location:
            loca.append(1)
        else:
            loca.append(0)

    highest = -pQueue.get()
    while True:
        pop = queue.popleft()
        loc = loca.popleft()
        if pop == highest:
            answer += 1
            if loc == 1:
                break
            else:
                highest = -pQueue.get()

        else:
            queue.append(pop)
            loca.append(loc)
    return answer


print(solution([2, 1, 3, 2], 2))  # 1
print(solution([1, 1, 9, 1, 1, 1], 0))  # 5
