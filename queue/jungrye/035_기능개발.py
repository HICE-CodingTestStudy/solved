from collections import deque


def solution(progresses, speeds):
    answer = []
    pQueue = deque()
    sQueue = deque()
    done = deque()

    for i in progresses:
        pQueue.append(i)
    for i in speeds:
        sQueue.append(i)

    while len(pQueue) != 0:
        curr = pQueue.popleft()
        speed = sQueue.popleft()
        day = (100-curr)//speed
        if (100-curr) % speed:
            day += 1
        done.append(day)

    day = done.popleft()
    count = 1
    while len(done) != 0:
        nextDay = done.popleft()
        if day >= nextDay:
            count += 1
        else:
            answer.append(count)
            count = 1
            day = nextDay
    answer.append(count)

    return answer


print(solution([93, 30, 55], [1, 30, 5]), "\n")
print(solution([95, 90, 99, 99, 80, 99], [1, 1, 1, 1, 1, 1]), "\n")
print(solution([90, 90, 90, 90], [30, 1, 1, 1]), "\n")
