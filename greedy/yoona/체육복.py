def solution(n, lost, reserve):
    answer = 0
    del_reserve = list(set(reserve) - set(lost))
    del_lost = list(set(lost) - set(reserve))

    for i in del_reserve:
        if i - 1 in del_lost:
            del_lost.remove(i - 1)
        elif i + 1 in del_lost:
            del_lost.remove(i + 1)

    answer = n - len(del_lost)

    return answer
