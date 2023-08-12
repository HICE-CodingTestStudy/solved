from collections import deque


def solution(people, limit):
    answer = 0
    people.sort(reverse=True)
    deq = deque()
    for p in people:
        deq.append(p)
    while len(deq) != 0:
        if len(deq) == 1:
            answer += 1
            break
        big = deq.popleft()
        small = deq.pop()
        sum = big+small
        if sum > limit:
            deq.append(small)
        answer += 1
    return answer


print(solution([70, 50, 80, 50], 100))
print(solution([70, 50, 80], 100))
print(solution([40, 50, 150, 160], 200))
print(solution([100, 500, 500, 900, 950], 1000))
print(solution([10, 20, 30, 40, 50, 60, 70, 80, 90], 100))
