def solution(arr):
    answer = []

    # 배열 첫번째 요소 -> append
    # top == 숫자 -> append 안하고 넘어감
    # top != 숫자 -> append
    for i in arr:
        if len(answer) == 0:
            answer.append(i)
            continue
        if answer[-1] == i:
            continue
        else:
            answer.append(i)
    return answer


print(solution([1, 1, 3, 3, 0, 1, 1]))
