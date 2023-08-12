def solution(n, lost, reserve):
    answer = n-len(lost)

    newLost = []
    for i in lost:
        if i in reserve:
            del reserve[reserve.index(i)]
            answer += 1
        else:
            newLost.append(i)

    newLost.sort()
    reserve.sort()

    for i in range(len(newLost)):
        front = newLost[i]-1
        back = newLost[i]+1

        if front in reserve:
            del reserve[reserve.index(front)]
            answer += 1
        elif back in reserve:
            del reserve[reserve.index(back)]
            answer += 1

    return answer


print(solution(5, [2, 4], [1, 3, 5]))
print(solution(5, [2, 4], [3]))
print(solution(3, [3], [1]))
print(solution(5, [4, 5], [3, 4]))
print(solution(5, [1, 2, 3], [2, 3, 4]))
