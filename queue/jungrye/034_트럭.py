from collections import deque


def solution(bridge_length, weight, truck_weights):
    answer = 0
    bridge = deque()
    trucks = deque()
    for i in range(bridge_length):
        bridge.append(0)
    for i in truck_weights:
        trucks.append(i)

    sum = 0
    answer = 1
    while len(trucks) != 0:
        pop = trucks.popleft()
        sum -= bridge.pop()
        sum += pop
        if sum > weight:
            bridge.appendleft(0)
            sum -= pop
            trucks.appendleft(pop)
        else:
            bridge.appendleft(pop)
        answer += 1
    answer += (bridge_length-1)

    return answer


print(solution(2, 10, [7, 4, 5, 6]))  # 8
print(solution(100, 100, [10]))  # 101
print(solution(100, 100, [10, 10, 10, 10, 10, 10, 10, 10, 10, 10]))  # 110
print(solution(5, 5, [2, 2, 2, 2, 1, 1, 1, 1, 1]))  # 19
