from collections import deque


def solution(progresses, speeds):
    answer = []
    queue = deque()

    for i in range(len(progresses)):
        remain = 100 - progresses[i]

        if remain % speeds[i] == 0:
            queue.append(remain // speeds[i])
        else:
            queue.append((remain // speeds[i]) + 1)

    while queue:
        cnt = 1
        day = queue.popleft()

        while queue and day >= queue[0]:
            queue.popleft()
            cnt += 1

        answer.append(cnt)

    return answer
