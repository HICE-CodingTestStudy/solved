from collections import deque


def solution(priorities, location):
    priorities = deque([(i, p) for i, p in enumerate(priorities)])

    cnt = 0
    while priorities:
        max_value = max(priorities, key=lambda x: x[1])[1]
        index, value = priorities.popleft()

        if value < max_value:
            priorities.append((index, value))

        elif value == max_value:
            cnt += 1
            if location == index:
                break

    return cnt
