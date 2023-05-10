def solution(array, commands):
    answer = []
    for com in commands:
        i = com[0]-1
        j = com[1]
        k = com[2]-1
        sortedArr = sorted(array[i:j])
        answer.append(sortedArr[k])
    return answer


print(solution([1, 5, 2, 6, 3, 7, 4], [[2, 5, 3], [4, 4, 1], [1, 7, 3]]))
