from collections import deque


def solution(priorities, location):
    sorted_priorities = sorted(priorities, reverse=True)
    priorities = deque([(i, p) for i, p in enumerate(priorities)])
    cnt = 0
    for priority in sorted_priorities:
        while priorities:
            index, value = priorities.popleft()

            if value < priority:
                priorities.append((index, value))

            elif value == priority:
                cnt += 1
                if location == index:
                    return cnt
                break
