from collections import deque


def solution(bridge_length, weight, truck_weights):
    time, total_weight = 0, 0
    ongoing, wait = deque(), deque(truck_weights)

    while ongoing or wait:
        if ongoing:
            if ongoing[0][1] == bridge_length - 1:
                total_weight -= ongoing.popleft()[0]

            for index in range(len(ongoing)):
                ongoing[index][1] += 1

        if wait and total_weight + wait[0] <= weight and len(ongoing) <= bridge_length:
            total_weight += wait[0]
            ongoing.append([wait.popleft(), 0])
        time += 1

    return time
