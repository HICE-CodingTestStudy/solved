def solution(arr):
    answer = []

    for e in arr:
        if len(answer) == 0 or answer[-1] != e:
            answer.append(e)

    return answer
